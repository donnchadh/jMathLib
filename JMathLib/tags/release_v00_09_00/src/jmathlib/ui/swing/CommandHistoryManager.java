package jmathlib.ui.swing;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.text.DateFormat;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class CommandHistoryManager {

    private static CommandHistoryManager DEFAULT_INSTANCE;
    private static int AFTER_LAST = -99;
    
    private int selectedSession = 0;
    private int selectedIndex = AFTER_LAST;

    private Session currentSession;
    private ArrayList<Session> allSessions = new ArrayList<Session>();

    private static final String SERIALIZED_FILENAME = "session_history.dat";
    private static final String ROOT_NODENAME = "Sessions";
    private static final String SESSION_NODENAME = "Session";
    private static final String DATE_ATTRNAME = "Date";
    private static final String COMMAND_NODENAME = "Command";

    public static interface SessionCommandListener {

        public void commandAdded(Command command);
    }

    private ArrayList<SessionCommandListener> listeners;

    private CommandHistoryManager() {
        listeners = new ArrayList<SessionCommandListener>();
        loadSessionHistory();
        addSession(startCurrentSession());
        resetToLastCommand();
    }
    
    private void addSession(Session s) {
        allSessions.add(s);
        if (allSessions.size() > ApplicationConfiguration.getInstance().getIntProperty(ApplicationConfiguration.SESSION_HISTORY_SESSIONCOUNT_PROPERTY))
          allSessions.remove(0);        
    }

    public void addCommand(String command) {
        this.currentSession.addCommand(command);

        resetToLastCommand();
    }

    public String nextCommand() {
        //Check if hanging off the end of the history
        if (selectedIndex == AFTER_LAST) {
            return null;
        }
        selectedIndex++;
        //Check to see if the index is outside the current session command size
        if (selectedIndex >= allSessions.get(selectedSession).commandCount()) {
            selectedSession++;
            selectedIndex = 0;
            //Check to see if we have wrapped to the end of all sessions
            if (selectedSession >= allSessions.size()) {
                selectedSession = allSessions.size() - 1;
                selectedIndex = AFTER_LAST;
                return "";
            }
        }

        if (allSessions.get(selectedSession).commandCount() == 0) {
            return "";
        }
        return allSessions.get(selectedSession).getCommand(selectedIndex).getCommand();
    }

    public String prevCommand() {
        if (selectedSession < 0) {
            return null;
        }
        if ((selectedIndex == AFTER_LAST) && (allSessions.get(selectedSession).commandCount() > 0)) {
          selectedIndex = allSessions.get(selectedSession).commandCount() - 1;
        } else if (selectedIndex <= 0) {
            selectedSession--;
            if (selectedSession < 0) {
                selectedSession = 0;
                selectedIndex = 0;
                return null;
            }
            if (selectedSession >= 0 && allSessions.size() > selectedSession) {
                selectedIndex = allSessions.get(selectedSession).commandCount() - 1;
            } else {
                if (selectedSession < 0) {
                    selectedSession = 0;
                }
                return null;
            }
        } else {
            selectedIndex--; // moving index to prev command
        }

        if (allSessions.get(selectedSession).commandCount() == 0) {
            return null;
        }
        return allSessions.get(selectedSession).getCommand(selectedIndex).getCommand();
    }

    public String currentCommand() {
        if (selectedSession > 0 && selectedSession < allSessions.size()) {
            if (selectedIndex > 0 && selectedIndex < allSessions.get(selectedSession).commandCount()) {
                return allSessions.get(selectedSession).getCommand(selectedIndex).getCommand();
            }
        }
        return null;
    }

    public void resetToLastCommand() {
        selectedSession = allSessions.size() - 1;
        selectedIndex = AFTER_LAST;
    }

    public void clearCurrentSession() {
        currentSession.clearCommands();
        resetToLastCommand();
    }

    private Session startCurrentSession() {
        this.currentSession = new Session(new Date(System.currentTimeMillis()));
        return this.currentSession;
    }

    private void fireCommandAdded(Command command) {
        for (int i = 0; i < this.listeners.size(); i++) {
            this.listeners.get(i).commandAdded(command);
        }
    }

    public void addChangeListener(SessionCommandListener listener) {
        this.listeners.add(listener);
    }

    public void removeChangeListener(SessionCommandListener listener) {
        this.listeners.remove(listener);
    }

    public int getSessionCount() {
        return this.allSessions.size();
    }

    public Session getSession(int index) {
        return this.allSessions.get(index);
    }

    public int indexOfSession(Session session) {
        return this.allSessions.indexOf(session);
    }

    public class Session {

        private ArrayList<Command> commands;
        private Date startTime;

        public Session(Date sessionStartTime) {
            this.commands = new ArrayList<Command>();
            this.startTime = sessionStartTime;
        }

        Command addCommand(String command) {
            Command c = new Command(this, command);
            this.commands.add(c);
            if (commands.size() > ApplicationConfiguration.getInstance().getIntProperty(ApplicationConfiguration.SESSION_HISTORY_SESSIONCMDCOUNT_PROPERTY))
                commands.remove(0);
            fireCommandAdded(c);
            return c;
        }

        void clearCommands() {
            commands.clear();
        }

        public int commandCount() {
            return this.commands.size();
        }

        public Command getCommand(int index) {
            return this.commands.get(index);
        }

        public Date getStartTime() {
            return startTime;
        }

        public int indexOfCommand(String command) {
            return this.commands.indexOf(command);
        }

        public int compareTo(Object other) {
            return startTime.compareTo(((Session)other).startTime);
        }

        @Override
        public String toString() {
            return "Session: " + DateFormat.getDateTimeInstance().format(startTime);
        }
    }

    public class Command {

        private String command;
        private Session session;

        Command(Session session, String command) {
            this.command = command;
            this.session = session;
        }

        @Override
        public String toString() {
            return command;
        }

        public String getCommand() {
            return this.command;
        }

        public Session getSession() {
            return session;
        }
    }

    public static CommandHistoryManager getDefaultInstance() {
        if (DEFAULT_INSTANCE == null) {
            DEFAULT_INSTANCE = new CommandHistoryManager();
        }
        return DEFAULT_INSTANCE;
    }

    /*
     *
     * Session Reading and Writting
     */

    private void loadSessionHistory() {
        File dir = ApplicationConfiguration.getConfigurationDirectory();
        if (dir != null) {
            File f = new File(dir, SERIALIZED_FILENAME);
            if (f.exists() && f.canRead()) {
                FileInputStream in = null;
                try {
                    in = new FileInputStream(f);
                    DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                    Document doc = builder.parse(in);
                    Element root = doc.getDocumentElement();
                    Element[] kids = getElements(root);
                    for (int i = 0; i < kids.length; i++) {
                        if (kids[i].getNodeName().equals(SESSION_NODENAME)) {
                            String dateString = kids[i].getAttribute(DATE_ATTRNAME);
                            if (dateString == null) {
                                continue;
                            }
                            long date = Long.parseLong(dateString, 16);
                            Session s = new Session(new Date(date));
                            Element[] kids2 = getElements(kids[i]);
                            for (int j = 0; j < kids2.length; j++) {
                                if (kids2[j].getNodeName().equals(COMMAND_NODENAME)) {
                                    s.addCommand(kids2[j].getTextContent());
                                }
                            }
                            addSession(s);
                        }
                    }
                } catch (Exception ex) {
                    //What more to do?
                    //Need to create a message class to display errors.
                    ex.printStackTrace();
                } finally {
                    if (in != null) {
                        try {
                            in.close();
                        } catch (Exception e) {
                            //Do nothing.
                        }
                    }
                }
            }
        }
    }

    public void writeSessionHistory() {
        File dir = ApplicationConfiguration.getConfigurationDirectory();
        if (dir != null) {
            File f = new File(dir, SERIALIZED_FILENAME);
            if (!f.exists() || f.canWrite()) {
                FileOutputStream out = null;
                try {
                    out = new FileOutputStream(f);

                    DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                    Document doc = builder.newDocument();
                    Element root = doc.createElement(ROOT_NODENAME);
                    doc.appendChild(root);
                    for (int i = 0; i < allSessions.size(); i++) {
                        if (allSessions.get(i).commandCount() > 0) {
                            Element elem = doc.createElement(SESSION_NODENAME);
                            root.appendChild(elem);
                            elem.setAttribute(DATE_ATTRNAME, Long.toHexString(allSessions.get(i).getStartTime().getTime()).toUpperCase());
                            for (int j = 0; j < allSessions.get(i).commandCount(); j++) {
                                Element comElem = doc.createElement(COMMAND_NODENAME);
                                comElem.setTextContent(allSessions.get(i).getCommand(j).getCommand());
                                elem.appendChild(comElem);
                            }
                        }
                    }
                    Transformer transformer = TransformerFactory.newInstance().newTransformer();
                    DOMSource source = new DOMSource(root);
                    StreamResult result = new StreamResult(out);
                    transformer.transform(source, result);
                } catch (Exception ex) {
                  //TODO: Need to see if there is some sort of error reporting mechanism
                } finally {
                    try {
                    out.close();
                    } catch (IOException ex) {
                        //Nothing can be done.
                    }
                }
            }
        }
    }

    private static Element[] getElements(Element elem) {
        ArrayList<Element> children = new ArrayList<Element>();
        Node node = elem.getFirstChild();
        while (node != null) {
            switch (node.getNodeType()) {
                case Node.ELEMENT_NODE:
                    children.add((Element) node);
                    break;
            }
            node = node.getNextSibling();
        }
        return children.toArray(new Element[0]);
    }
}

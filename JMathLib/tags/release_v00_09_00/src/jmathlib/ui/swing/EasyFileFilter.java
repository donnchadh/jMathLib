package jmathlib.ui.swing;

import java.io.File;
import javax.swing.filechooser.*;

public class EasyFileFilter extends FileFilter implements java.io.FileFilter {

        private String[] fileExtensions;
        private String description;
        private boolean exceptsFolders;

        public EasyFileFilter(String[] fileExtensions, String description, boolean exceptsFolders) {
            this.fileExtensions = fileExtensions;
            this.description = description;
            this.exceptsFolders = exceptsFolders;
        }

        public boolean accept(File f) {
            //return false;
            if (f.isDirectory()) {
                return true;
                //return exceptsFolders;
            } else {
                for (int i = 0; i < fileExtensions.length; i++) {
                    if (f.getName().endsWith(fileExtensions[i])) {
                        return true;
                    }
                }
            }
            return false;
        }

        public String getDescription() {
            return description;
        }
    }
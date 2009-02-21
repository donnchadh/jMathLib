package jmathlib.plugins;



/**Class containing the extensions of jmathlib*/
public class Plugin 
{

    // name of the plugin
    protected String name;    

    private PluginsManager pluginsManager;
    
    public Plugin()
    {

	
    }

    public Plugin(String _name)
    {

	
    }
    
    public String getName()
    {
        return name;
    }


    public void setPluginsManager(PluginsManager _pluginsManager)
    {
        pluginsManager = _pluginsManager ;
    }

    public PluginsManager getPluginsManager()
    {
        return pluginsManager;
    }
    
    public void init()
    {
    
    }

      
}

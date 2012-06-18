package com.sessonad.oscommands.commands;


import com.sessonad.oscommands.detector.OSDetector;
import com.sessonad.oscommands.detector.OperatingSystem;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;


/**
 *
 * @author SessonaD
 */
public abstract class Commands {
    
    static Commands platform;
    
    @SuppressWarnings("unchecked")
    public static <T extends Commands> T getPlatform(){
        if (platform == null)initializePlatform();
        return (T)platform;
    }
    
    static void initializePlatform(){
        OperatingSystem os = OSDetector.detectOS();
        if      (os.equals(OperatingSystem.WINDOWS))    platform = new WindowsCommands();
        else if (os.equals(OperatingSystem.MAC_OS))     platform = new MacOSCommands();
        else if (os.equals(OperatingSystem.LINUX_GNOME))platform = new LinuxGnomeCommands();        
        else if (os.equals(OperatingSystem.LINUX_KDE))  platform = new LinuxKdeCommands();
        else if (os.equals(OperatingSystem.LINUX_LXDE)) platform = new LinuxLxdeCommands();
        else if (os.equals(OperatingSystem.LINUX_XFCE)) platform = new LinuxXfceCommands();
        else if (os.equals(OperatingSystem.LINUX_UNKNOWN)) platform = new LinuxUnknownCommands();
        else if (os.equals(OperatingSystem.UNKNOWN))    platform = new UnknownOSCommands();
    }
    
    public abstract OperatingSystem getOperatingSystem();
    
    public void openInShell(String currentPath) throws Exception {
        String fullCommand = getOperatingSystem().getShellCommand() + currentPath;
        Runtime.getRuntime().exec(fullCommand);
    }
    
    public void browseInFileSystem(File current) throws Exception {
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.OPEN)) {
            try {
                Desktop.getDesktop().open(current);
            } catch (IOException e) {
                executeFileSystemBrowserCommand(current);
            }
        } else {
            executeFileSystemBrowserCommand(current);
        }
    }

    protected void executeFileSystemBrowserCommand(File current) throws IOException {
        String fullCommand = getOperatingSystem().getFileSystemBrowserCommand() + current.getAbsolutePath();
        Runtime.getRuntime().exec(fullCommand);
    }
    

}

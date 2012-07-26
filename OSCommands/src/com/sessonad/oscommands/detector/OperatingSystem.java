package com.sessonad.oscommands.detector;

/**
 *
 * @author SessonaD
 */
public enum OperatingSystem {
    
    WINDOWS     ("cmd /c start ","cmd /c start cd ",null),
    MAC_OS      ("open -a /Applications/Utilities/Terminal.app ","open -a /Applications/Utilities/Terminal.app ","open -R "),
    LINUX_GNOME ("gnome-terminal ","gnome-terminal --working-directory=","nautilus "),
    LINUX_KDE   ("konsole ","konsole --workdir ","dolphin "),
    LINUX_XFCE  ("exo-open --launch TerminalEmulator ","exo-open --launch TerminalEmulator --working-directory ","thunar "),
    LINUX_LXDE  ("lxterminal ","lxterminal --working-directory=","pcmanfm "),
    LINUX_UNKNOWN(null,null,null),
    UNKNOWN     (null,null,null);
    
    private String shellPrefix;
    private String shellCommand;    
    private String fileSystemBrowserCommand;

    private OperatingSystem(String shellPrefix,String shellCommand, String fileSystemBrowserCommand) {
        this.shellPrefix = shellPrefix;
        this.shellCommand = shellCommand;
        this.fileSystemBrowserCommand = fileSystemBrowserCommand;
    }

    public String getFileSystemBrowserCommand() {
        return fileSystemBrowserCommand;
    }

    public String getShellCommand() {
        return shellCommand;
    }

    public String getShellPrefix() {
        return shellPrefix;
    }
    
}

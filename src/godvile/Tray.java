package godvile;

/**
 * Created by mark on 26.08.16.
 */

import dorkbox.systemTray.MenuEntry;
import dorkbox.systemTray.SystemTray;
import dorkbox.systemTray.SystemTrayMenuAction;

import javax.swing.*;
import java.io.IOException;

class Tray implements Runnable{
SystemTray systemTray;

    @Override
    public void run() {
        systemTray = SystemTray.getSystemTray();
        if (systemTray == null) {
            throw new RuntimeException("Unable to load SystemTray!");
        }
        systemTray.setIcon("/home/mark/Загрузки/TIJ4-code/Godvile/src/images/icon_128.png");
        systemTray.addMenuEntry("Quit", new SystemTrayMenuAction() {
            @Override
            public
            void onClick(final SystemTray systemTray, final MenuEntry menuEntry) {
                System.exit(0);
            }
        });

    }
}

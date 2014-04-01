/**
 * 
 */
package com.angelis.tera.packet.captor;

import java.awt.SplashScreen;

import com.angelis.tera.packet.MainPacket;

/**
 * @author Ulysses R. Ribeiro
 *
 */
public class ConsoleUserInterface implements IUserInterface
{

    public ConsoleUserInterface()
    {
        SplashScreen.getSplashScreen().close();
    }
    
    public void log(String text)
    {
        System.out.println(text);
    }
    
    public void close()
    {
        MainPacket.saveConfig();
    }
}

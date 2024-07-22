package Zigbee.ember;

import javax.swing.JTextArea;

public class ProcessMessage {
	
	public ProcessMessage()
	{}
	
	public void CheckMessage(String message, boolean check, JTextArea txtArea)
	{
		String msg = message.trim();
		if(msg.length()== 0)
			return;
		if(msg.indexOf("assertme - ") == 0 )
				return;
                if(msg.indexOf("reboot - ") == 0 )
                        return;
                if(msg.indexOf("assertme - ") == 0 )
                        return;
                if(msg.indexOf("adctest - ") == 0 )
                        return;
                if(msg.indexOf("button <int8u> - ") == 0 )
                        return;
                if(msg.indexOf("ledon <int8u> - ") == 0 )
                        return;
                if(msg.indexOf("ledoff <int8u> - ") == 0 )
                        return;
                if(msg.indexOf("ledtoggle <int8u> - ") == 0 )
                        return;
                if(msg.indexOf("setgpio <int8u> <int8u> - ") == 0 )
                        return;
                if(msg.indexOf("cleargpio <int8u> <int8u> - ") == 0 )
                        return;
                if(msg.indexOf("readgpio <int8u> <int8u> - ") == 0 )
                        return;
                if(msg.indexOf("toggleuart - ") == 0 )
                        return;
                if(msg.indexOf("tune - ") == 0 )
                        return;
                if(msg.indexOf("play <int16u> - ") == 0 )
                        return;
                if(msg.indexOf("systmrtest - ") == 0 )
                        return;
                if(msg.indexOf("spiptmrtest - ") == 0 )
                        return;
                if(msg.indexOf("shutdown - ") == 0 )
                        return;
                if(msg.indexOf("dfinit <int8u> - ") == 0 )
                        return;
                if(msg.indexOf("dfread <int32u> <int16u> - ") == 0 )
                        return;
                if(msg.indexOf("dfwrite <int32u> <int16u> - ") == 0 )
                        return;
                if(msg.indexOf("dferase <int32u> <int16u> - ") == 0 )
                        return;
                if(msg.indexOf("dfpwrdn - ") == 0 )
                        return;
                if(msg.indexOf("help - ") == 0 )
                        return;
                if(msg.indexOf("dfpwrup - ") == 0 )
                        return;
                if(msg.indexOf("? - ") == 0 )
                        return;	
                if(msg.indexOf("ledtest - ") == 0 )
                        return;	

	Printf(msg, txtArea);
		
	}
	
	public void Printf(String message, JTextArea txtArea)
	{
		txtArea.append(message +"\n");
		txtArea.setCaretPosition(txtArea.getDocument().getLength());
	}
        
        public void Help( JTextArea txtArea)
        {
           //Thread thread = new Thread();
          // thread.wait(100);                    
          // Printf("ddd", txtArea);
        }

}

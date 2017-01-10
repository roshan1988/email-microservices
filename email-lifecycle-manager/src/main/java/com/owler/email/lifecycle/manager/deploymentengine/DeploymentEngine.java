package com.owler.email.lifecycle.manager.deploymentengine;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.stereotype.Component;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.owler.email.lifecycle.manager.deploymentrules.DeploymentRule;

@Component
public class DeploymentEngine{
		
	public boolean scaleUp(DeploymentRule rule, String serviceId){
		if(! rule.execute()) {
			return false;
		}
		Runnable runnable = () -> {
			System.out.println("Kicking off a new instance "+ serviceId);	
		    executeSSH();
		};

		Thread thread = new Thread(runnable);
		thread.start();
		
		return true;
	}
	
	private boolean executeSSH(){ 
		//get deployment descriptor, instead of this hard coded.
		// or execute a script on the target machine which download artifact from nexus
        String command ="nohup java -jar -Dserver.port=8091 location /Users/roshan/Work/search-1.0.jar &";
       try{	
    	   System.out.println("Executing "+ command);
           java.util.Properties config = new java.util.Properties(); 
           config.put("StrictHostKeyChecking", "no");
           JSch jsch = new JSch();
           Session session=jsch.getSession("roshan", "localhost", 22);
           
           session.setConfig(config);
           session.connect();
           System.out.println("Connected");
            
           ChannelExec channelExec = (ChannelExec)session.openChannel("exec");
           InputStream in = channelExec.getInputStream();
           channelExec.setCommand(command);
           channelExec.connect();
          
           BufferedReader reader = new BufferedReader(new InputStreamReader(in));
           String line;
           int index = 0;

           while ((line = reader.readLine()) != null) {
               System.out.println(++index + " : " + line);
           }
           channelExec.disconnect();
           session.disconnect();

           System.out.println("Done!");

       }catch(Exception e){
           e.printStackTrace();
           return false;
       }
		
		return true;
	}
	
	public boolean scaleDown(DeploymentRule rule, String serviceId){
		System.out.println("Scaling down instance");
		return true;
	}

}
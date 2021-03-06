package app.songy.com.zjdroid.apimonitor;

import java.lang.reflect.Method;
import java.util.List;

import app.songy.com.zjdroid.hook.HookParam;
import app.songy.com.zjdroid.util.Logger;
import app.songy.com.zjdroid.util.RefInvoke;


public class ProcessBuilderHook extends ApiMonitorHook {

	@Override
	public void startHook() {
		// TODO Auto-generated method stub
		Method execmethod = RefInvoke.findMethodExact(
				"java.lang.ProcessBuilder", ClassLoader.getSystemClassLoader(),
				"start");
		hookhelper.hookMethod(execmethod, new AbstractBahaviorHookCallBack() {			
			@Override
			public void descParam(HookParam param) {
				// TODO Auto-generated method stub
				Logger.log_behavior("Create New Process ->");
				ProcessBuilder pb = (ProcessBuilder) param.thisObject;
				List<String> cmds = pb.command();
				StringBuilder sb = new StringBuilder();
				for(int i=0 ;i <cmds.size(); i++){
				   sb.append("CMD"+i+":"+cmds.get(i)+" ");
				}
				Logger.log_behavior("Command" + sb.toString());
			}
		});
	}

}

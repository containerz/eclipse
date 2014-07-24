package floobits.impl;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.WorkbenchWindow;

import floobits.common.EditorEventHandler;
import floobits.common.Ignore;
import floobits.common.RunLater;
import floobits.common.interfaces.IContext;
import floobits.common.protocol.FlooUser;
import floobits.utilities.Flog;

public class ContextImpl extends IContext {
	
	public IWorkspace workspace;

	public ContextImpl(IWorkspace workspace) throws CoreException {
		this.workspace = workspace;
		this.iFactory = new FactoryImpl(workspace, this);
	}

	@Override
	protected void shareProjectDialog(String name, List<String> orgs,
			String host, boolean _private_, String projectPath) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String selectAccount(String[] keys) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getActualContext() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void loadChatManager() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void flashMessage(String message) {
		Flog.log("%s", message);
		
	}

	@Override
	public void warnMessage(String message) {
		Flog.warn(message);
		
	}

	@Override
	public void statusMessage(final String message) {
        Display.getDefault().syncExec(new Runnable() {
            public void run() {
                IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
                WorkbenchWindow w = (WorkbenchWindow) window;
                w.getStatusLineManager().setMessage(message);
            }
        });
	}

	@Override
	public void errorMessage(String message) {
		Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
		MessageDialog.openQuestion(shell, "Warning", message);
	}

	@Override
	public void chat(String username, String msg, Date messageDate) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void openChat() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void listenToEditor(EditorEventHandler editorEventHandler) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setUsers(Map<Integer, FlooUser> users) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setListener(boolean b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mainThread(Runnable runnable) {
		runnable.run();
		
	}

	@Override
	public void readThread(Runnable runnable) {
		runnable.run();
		
	}

	@Override
	public void writeThread(Runnable runnable) {
		runnable.run();
		
	}

	@Override
	public void dialog(String title, String body, RunLater<Boolean> runLater) {
		Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
		boolean yes = MessageDialog.openQuestion(shell, title, body);
		runLater.run(yes);
	}

	@Override
	public void dialogDisconnect(int tooMuch, int howMany) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dialogPermsRequest(String username, RunLater<String> perms) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean dialogTooBig(LinkedList<Ignore> tooBigIgnores) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void dialogResolveConflicts(Runnable stompLocal,
			Runnable stompRemote, boolean readOnly, Runnable flee,
			String[] conflictedPathsArray) {
		// TODO Auto-generated method stub
		
	}

}

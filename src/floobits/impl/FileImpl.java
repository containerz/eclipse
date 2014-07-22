package floobits.impl;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import floobits.common.interfaces.IContext;
import floobits.common.interfaces.IFile;
import floobits.utilities.Flog;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.eclipse.core.filebuffers.FileBuffers;
import org.eclipse.core.filebuffers.IFileBuffer;
import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileInfo;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;

public class FileImpl extends IFile {
	public IFileStore file;
	protected IFileBuffer buffer;
	private IContext context;

	public FileImpl(IContext context, IFileStore file) {
		this.file = file;
		this.context = context;
		FileBuffers.getTextFileBufferManager();
	}
	
	public IFileInfo getInfo() {
		return file.fetchInfo();
	}

	@Override
	public String getPath() {
		return file.fetchInfo().toString();
	}

	@Override
	public boolean rename(Object obj, String name) {
//		// TODO Auto-generated method stub
//		IPath path = file.getLocation();
//		path.uptoSegment(0).append(name);
//		file.getFileStore().g
//		file.getFileStore().move(path., 0, null)
		return false;
	}

	@Override
	public IFile makeFile(String name) {
		IFileStore child;
		try {
			file.mkdir(0, null);
			child = file.getChild(name);
			File localFile = child.toLocalFile(0, null);
			localFile.createNewFile();
		} catch (Throwable e) {
			Flog.warn(e);
			return null;
		}
		return new FileImpl(context, child);

	}

	@Override
	public boolean move(Object obj, IFile d) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Object obj) {
		try {
			file.delete(0, null);
		} catch (CoreException e) {
			Flog.warn(e);
			return false;
		}
		return true;
	}

	@Override
	public IFile[] getChildren() {
		IFileStore[] childStores;
		ArrayList<IFile> list = new ArrayList<>();
		try {
			childStores = file.childStores(0, null);
		} catch (CoreException e) {
			Flog.warn(e);
			return (IFile[]) list.toArray();
		}
		for (IFileStore fileStore : childStores) {
			FileImpl fileImpl = new FileImpl(context, fileStore);
			list.add(fileImpl);
		}
		return (IFile[]) list.toArray();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return file.getName();
	}

	@Override
	public long getLength() {
		IFileInfo info = getInfo();
		return info.getLength();
	}

	@Override
	public boolean exists() {
		try {
			return file.toLocalFile(0, null).exists();
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			return false;
		}
	}

	@Override
	public boolean isDirectory() {
		IFileInfo info = getInfo();
		return info.isDirectory();
	}

	@Override
	public boolean isSpecial() {
		return false;
	}

	@Override
	public boolean isSymLink() {
		return getInfo().getAttribute(EFS.ATTRIBUTE_LINK_TARGET);
	}

	@Override
	public boolean isValid() {
		return true;
	}

	@Override
	public byte[] getBytes() {
		InputStream in;
		try {
			in = file.openInputStream(0, null);
		} catch (CoreException e) {
			Flog.warn(e);
			return null;
		}
		byte[] bytes = null;
		try {
			bytes = IOUtils.toByteArray(in);
		} catch (IOException e) {
			Flog.warn(e);
			return null;
		}
		return bytes;
	}

	@Override
	public boolean setBytes(byte[] bytes) {
		ByteArrayInputStream io = new ByteArrayInputStream(bytes);
		try {
			IOUtils.copy(io, file.openOutputStream(0, null));
		} catch (Throwable e) {
			Flog.warn(e);
			return false;
		}
		return true;
	}

	@Override
	public void refresh() {
		
	}

	@Override
	public boolean createDirectories(String dir) {
		IFileStore child = file.getChild(dir);
		try {
			child.mkdir(EFS.NONE, null);
		} catch (CoreException e) {
			Flog.warn(e);
			return false;
		}
		return true;
	}

	@Override
	public InputStream getInputStream() {
		// TODO Auto-generated method stub
		try {
			return file.openInputStream(0, null);
		} catch (CoreException e) {
			Flog.warn(e);
		}
		return null;
	}

}

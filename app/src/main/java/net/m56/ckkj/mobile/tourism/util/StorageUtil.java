package net.m56.ckkj.mobile.tourism.util;

import android.content.Context;
import android.os.Environment;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
* @ClassName: StorageUtil
* @Description: 读写文件
* @author liuqi qiliu_17173@cyou-inc.com
* @date 2014-8-4 下午4:59:27
 */
public class StorageUtil {
	public static final String FILE_NAME_TMP = "tmp";
	public static final String FILE_NAME_RCMD = "rcmd";
	public static final String DIR_NAME_AD = "AD2";
	public static final String DIR_NAME_APP = "downlaodsApp";
	public static final String FILE_NAME_AD_MANIFEST = "admanifest";

	private static StorageUtil sInstance;
	private static ExecutorService sExecutors;
	private static String sRootDir;

	private StorageUtil() {
		File fileDir = new File(sRootDir);
		if (!fileDir.exists())
			fileDir.mkdirs();
	}

	public static synchronized StorageUtil getInstance() {
		if (sInstance == null)
			init();
		return sInstance;
	}

	private static void init() {
		sExecutors = Executors.newSingleThreadScheduledExecutor();
		sRootDir = Environment.getExternalStorageDirectory().getPath()
				+ "/xs";
		sInstance = new StorageUtil();
	}

	public static String getFileDir() {
		if (sRootDir == null) {
			sRootDir = Environment.getExternalStorageDirectory().getPath()
					+ "/xs";
		}
		return sRootDir;
	}

	public static File getFile(String fileName) {
		return new File(getFileDir(), fileName);
	}

	public static boolean hasSdcard() {
		String state = Environment.getExternalStorageState();
		if (state.equals(Environment.MEDIA_MOUNTED)) {
			return true;
		} else {
			return false;
		}
	}


	/**
	 * 获取文件夹的大小
	 *
	 * wangqing 2013-9-24 double
	 *
	 * @param file
	 * @return
	 */
	public static long getDirSize(File file) {
		// 判断文件是否存在
		if (file.exists()) {
			// 如果是目录则递归计算其内容的总大小
			if (file.isDirectory()) {
				File[] children = file.listFiles();
				long size = 0;
				for (File f : children)
					size += getDirSize(f);
				return size;
			} else {// 如果是文件则直接返回其大小,以“兆”为单位
				return file.length();
			}
		} else {
			System.out.println("文件或者文件夹不存在，请检查路径是否正确！");
			return 0l;
		}
	}

	/**
	 * @deprecated Should use the async method instead
	 */
	private void saveFile(File file, String content, boolean deleteIfExist) {

		// Check the flag and whether the file exist
		if (!deleteIfExist && file.exists()) {
			return;
		} else if (deleteIfExist && file.exists()) {
			file.delete();
		}

		BufferedOutputStream out = null;
		File parentFile = file.getParentFile();
		File tempFile = new File(parentFile, FILE_NAME_TMP + file.getName());
		try {
			if (!parentFile.exists()) {
				parentFile.mkdirs();
			}
			if (!tempFile.exists()) {
				tempFile.createNewFile();
			} else {
				tempFile.delete();
				tempFile.createNewFile();
			}

			out = new BufferedOutputStream(new FileOutputStream(tempFile));
			byte[] buffer = content.getBytes("UTF-8");
			out.write(buffer, 0, buffer.length);
			out.flush();
			if (!file.exists()) {
				file.createNewFile();
			} else if (deleteIfExist) {
				file.delete();
				file.createNewFile();
			} else {
				return;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null)
					out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void saveFileAsync(final String filePath, final String content) {
		File file = new File(filePath);
		saveFileAsync(file, content);
	}

	public void saveFileAsync(final File file, final String content) {
		saveFileAsync(file, content, false);
	}

	public void saveFileAsync(final File file, final String content,
                              final boolean deleteIfExist) {
		sExecutors.submit(new Runnable() {
			@Override
			public void run() {
				saveFile(file, content, deleteIfExist);
			}
		});
	}

	/**
	 * @deprecated Should use the async method instead
	 */
	public void saveFileFromHttp(String urlString, File destFile,
                                 boolean deleteIfExist) {

		// Check the flag and whether the file exist
		if (!deleteIfExist && destFile.exists()) {
			return;
		} else if (deleteIfExist && destFile.exists()) {
			destFile.delete();
		}

		HttpURLConnection urlConnection = null;
		BufferedOutputStream out = null;
		BufferedInputStream in = null;
		File tmpFile = new File(destFile.getParentFile(), FILE_NAME_TMP
				+ urlString.hashCode());
		try {
			final URL url = new URL(urlString);
			urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setFollowRedirects(false);
			urlConnection.setConnectTimeout(30 * 1000);
			urlConnection.setReadTimeout(30 * 1000);

			in = new BufferedInputStream(urlConnection.getInputStream(), 4096);
			File parentFile = tmpFile.getParentFile();
			if (!parentFile.exists()) {
				parentFile.mkdirs();
			}
			if (!tmpFile.exists()) {
				tmpFile.createNewFile();
			} else {
				tmpFile.delete();
				tmpFile.createNewFile();
			}

			// Download the file to temp file
			InputStream input = new BufferedInputStream(
					urlConnection.getInputStream());
			byte[] buffer = new byte[4096];
			out = new BufferedOutputStream(new FileOutputStream(tmpFile));
			int length = input.read(buffer);
			while (length != -1) {
				out.write(buffer, 0, length);
				out.flush();
				length = input.read(buffer);
			}

			if (!deleteIfExist && destFile.exists()) {
				return;
			} else if (deleteIfExist && destFile.exists()) {
				destFile.delete();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null)
					out.close();
				if (in != null)
					in.close();
				if (urlConnection != null)
					urlConnection.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void saveFileFromHttpAsync(final String urlString,
			final File destFile) {
		saveFileFromHttpAsync(urlString, destFile, false);
	}

	public void saveFileFromHttpAsync(final String urlString,
                                      final File destFile, final boolean deleteIfExist) {
		sExecutors.submit(new Runnable() {
			@Override
			public void run() {
				saveFileFromHttp(urlString, destFile, deleteIfExist);
			}
		});
	}

	/**
	 * 将一个InputStream里面的数据写入到SD卡中
	 *
	 * @param input
	 * @return
	 */
	public static boolean write2SDFromInput(Context context, InputStream input) {
		File file = null;
		OutputStream output = null;
		try {
			file = new File(Utils.getDiskCacheDir(context, "share"), "newsTemp");
			output = new FileOutputStream(file);
			byte[] buffer = new byte[4096];

			/*
			 * 真机测试，这段可能有问题，请采用下面网友提供的 while((input.read(buffer)) != -1){
			 * output.write(buffer); }
			 */

			/* 网友提供 begin */
			int length;
			while ((length = (input.read(buffer))) > 0) {
				output.write(buffer, 0, length);
			}
			/* 网友提供 end */
			output.flush();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (output != null) {
					output.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

}

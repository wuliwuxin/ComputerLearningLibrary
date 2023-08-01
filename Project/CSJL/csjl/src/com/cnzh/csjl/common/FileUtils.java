package com.cnzh.csjl.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.math.BigInteger;
import java.security.MessageDigest;


/**
 * @desc:文件工具类
 * 
 * @Author:chenssy
 * @date:2014年8月7日
 */
public class FileUtils {
	private static final String FOLDER_SEPARATOR = "/";
	private static final char EXTENSION_SEPARATOR = '.';
	
	/**
	 * @desc:判断指定路径是否存在，如果不存在，根据参数决定是否新建
	 * @autor:chenssy
	 * @date:2014年8月7日
	 *
	 * @param filePath
	 * 			指定的文件路径
	 * @param isNew
	 * 			true：新建、false：不新建
	 * @return 存在返回TRUE，不存在返回FALSE
	 */
	public static boolean isExist(String filePath,boolean isNew){
		File file = new File(filePath);
		if(!file.exists() && isNew){    
			return file.mkdirs();    //新建文件路径
		}
		return false;
	}
	
	/**
	 * 获取文件名，构建结构为 prefix + yyyyMMddHH24mmss + 10位随机数 + suffix + .type
	 * @autor:chenssy
	 * @date:2014年8月11日
	 *
	 * @param type
	 * 				文件类型
	 * @param prefix
	 * 				前缀
	 * @param suffix
	 * 				后缀
	 * @return
	 */
	public static String getFileName(String type,String prefix,String suffix){
		String date = DateUtils.getCurrentTime("yyyyMMddHH24mmss");   //当前时间
		String random = RandomUtils.generateNumberString(10);   //10位随机数
		
		//返回文件名  
		return prefix + date + random + suffix + "." + type;
	}
	
	/**
	 * 获取文件名，文件名构成:当前时间 + 10位随机数 + .type
	 * @autor:chenssy
	 * @date:2014年8月11日
	 *
	 * @param type
	 * 				文件类型
	 * @return
	 */
	public static String getFileName(String type){
		return getFileName(type, "", "");
	}
	
	/**
	 * 获取文件名，文件构成：当前时间 + 10位随机数
	 * @autor:chenssy
	 * @date:2014年8月11日
	 *
	 * @return
	 */
	public static String getFileName(){
		String date = DateUtils.getCurrentTime("yyyyMMddHH24mmss");   //当前时间
		String random = RandomUtils.generateNumberString(10);   //10位随机数
		
		//返回文件名  
		return date + random;
	}
	
	/**
	 * 获取指定文件的大小
	 *
	 * @param file
	 * @return
	 * @throws Exception
	 *
	 * @author:chenssy
	 * @date : 2016年4月30日 下午9:10:12
	 */
	@SuppressWarnings("resource")
	public static long getFileSize(File file) throws Exception {
		long size = 0;
		if (file.exists()) {
			FileInputStream fis = null;
			fis = new FileInputStream(file);
			size = fis.available();
		} else {
			file.createNewFile();
		}
		return size;
	}
	
	/**
	 * 删除所有文件，包括文件夹
	 * 
	 * @author : chenssy
	 * @date : 2016年5月23日 下午12:41:08
	 *
	 * @param dirpath
	 */
    public void deleteAll(String dirpath) {  
    	 File path = new File(dirpath);  
         try {  
             if (!path.exists())  
                 return;// 目录不存在退出   
             if (path.isFile()) // 如果是文件删除   
             {  
                 path.delete();  
                 return;  
             }  
             File[] files = path.listFiles();// 如果目录中有文件递归删除文件   
             for (int i = 0; i < files.length; i++) {  
                 deleteAll(files[i].getAbsolutePath());  
             }  
             path.delete();  

         } catch (Exception e) {  
             e.printStackTrace();  
         }   
    }
    
    /**
     * 复制文件或者文件夹
     * 
     * @author : chenssy
     * @date : 2016年5月23日 下午12:41:59
     *
     * @param inputFile
     * 						源文件
     * @param outputFile
     * 						目的文件
     * @param isOverWrite
     * 						是否覆盖文件
     * @throws java.io.IOException
     */
    public static void copy(File inputFile, File outputFile, boolean isOverWrite)
			throws IOException {
		if (!inputFile.exists()) {
			throw new RuntimeException(inputFile.getPath() + "源目录不存在!");
		}
		copyPri(inputFile, outputFile, isOverWrite);
	}
    
    /**
     * 复制文件或者文件夹
     * 
     * @author : chenssy
     * @date : 2016年5月23日 下午12:43:24
     *
     * @param inputFile
     * 						源文件
     * @param outputFile
     * 						目的文件
     * @param isOverWrite
     * 						是否覆盖文件
     * @throws java.io.IOException
     */
    private static void copyPri(File inputFile, File outputFile, boolean isOverWrite) throws IOException {
		if (inputFile.isFile()) {		//文件
			copySimpleFile(inputFile, outputFile, isOverWrite);
		} else {
			if (!outputFile.exists()) {		//文件夹	
				outputFile.mkdirs();
			}
			// 循环子文件夹
			for (File child : inputFile.listFiles()) {
				copy(child, new File(outputFile.getPath() + "/" + child.getName()), isOverWrite);
			}
		}
	}
    
    /**
     * 复制单个文件
     * 
     * @author : chenssy
     * @date : 2016年5月23日 下午12:44:07
     *
     * @param inputFile
     * 						源文件
     * @param outputFile
     * 						目的文件
     * @param isOverWrite
     * 						是否覆盖
     * @throws java.io.IOException
     */
    private static void copySimpleFile(File inputFile, File outputFile,
			boolean isOverWrite) throws IOException {
		if (outputFile.exists()) {
			if (isOverWrite) {		//可以覆盖
				if (!outputFile.delete()) {
					throw new RuntimeException(outputFile.getPath() + "无法覆盖！");
				}
			} else {
				// 不允许覆盖
				return;
			}
		}
		InputStream in = new FileInputStream(inputFile);
		OutputStream out = new FileOutputStream(outputFile);
		byte[] buffer = new byte[1024];
		int read = 0;
		while ((read = in.read(buffer)) != -1) {
			out.write(buffer, 0, read);
		}
		in.close();
		out.close();
	}
    
    /**
     * 获取文件的MD5
     * 
     * @author : chenssy
     * @date : 2016年5月23日 下午12:50:38
     *
     * @param file
     * 				文件
     * @return
     */
	public static String getFileMD5(File file){
		if (!file.exists() || !file.isFile()) {  
            return null;  
        }  
        MessageDigest digest = null;  
        FileInputStream in = null;  
        byte buffer[] = new byte[1024];  
        int len;  
        try {  
            digest = MessageDigest.getInstance("MD5");  
            in = new FileInputStream(file);  
            while ((len = in.read(buffer, 0, 1024)) != -1) {  
                digest.update(buffer, 0, len);  
            }  
            in.close();  
        } catch (Exception e) {  
            e.printStackTrace();  
            return null;  
        }  
        BigInteger bigInt = new BigInteger(1, digest.digest());  
        return bigInt.toString(16);  
	}
	
	/**
	 * 获取文件的后缀
	 * 
	 * @author : chenssy
	 * @date : 2016年5月23日 下午12:51:59
	 *
	 * @param file
	 * 				文件
	 * @return
	 */
	public static String getFileSuffix(String file) {
		if (file == null) {
			return null;
		}
		int extIndex = file.lastIndexOf(EXTENSION_SEPARATOR);
		if (extIndex == -1) {
			return null;
		}
		int folderIndex = file.lastIndexOf(FOLDER_SEPARATOR);
		if (folderIndex > extIndex) {
			return null;
		}
		return file.substring(extIndex + 1);
	}
	
	/**
	 * 文件重命名
	 * 
	 * @author : chenssy
	 * @date : 2016年5月23日 下午12:56:05
	 *
	 * @param oldPath
	 * 					老文件
	 * @param newPath
	 * 					新文件
	 */
    public boolean renameDir(String oldPath, String newPath) {  
        File oldFile = new File(oldPath);// 文件或目录   
        File newFile = new File(newPath);// 文件或目录   
        
        return oldFile.renameTo(newFile);// 重命名   
    }
    
    /**  
     * 以字符为单位读取文件，常用于读文本，数字等类型的文件  
    */  
    public static void readFileByChars(String fileName) {  
        File file = new File(fileName);  
        Reader reader = null;  
        try {  
            System.out.println("以字符为单位读取文件内容，一次读一个字符：");  
            // 一次读一个字符  
            reader = new InputStreamReader(new FileInputStream(file));  
            int tempchar;  
            while ((tempchar = reader.read()) != -1) {  
               // 对于windows下，\r\n这两个字符在一起时，表示一个换行。  
               // 但如果这两个字符分开显示时，会换两次行。  
               // 因此，屏蔽掉\r，或者屏蔽\n。否则，将会多出很多空行。  
               if (((char) tempchar) != '\r') {  
                    System.out.print((char) tempchar);                   
               }  
            }  
            System.out.println();  
            reader.close();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        try {  
            System.out.println("以字符为单位读取文件内容，一次读多个字符：");  
            // 一次读多个字符  
            char[] tempchars = new char[32];  
             
            int charread = 0;  
            reader = new InputStreamReader(new FileInputStream(fileName));  
            // 读入多个字符到字符数组中，charread为一次读取字符数  
            while ((charread = reader.read(tempchars)) != -1) {  
                // 同样屏蔽掉\r不显示  
                if ((charread == tempchars.length) && (tempchars[tempchars.length - 1] != '\r')){  
                    System.out.print(tempchars);  
                }else{  
                    for(int i = 0; i < charread; i++){  
                        if (tempchars[i] == '\r') {  
                            continue;  
                        } else {  
                            System.out.print(tempchars[i]);  
                        }  
                    }  
                }  
            }  
            System.out.println();  
        } catch (Exception e1) {  
            e1.printStackTrace();  
        } finally {  
            if (reader != null) {  
                try {  
                    reader.close();  
                } catch (IOException e1) {  
                }  
            }  
        }  
    } 
    
    /**  
     * A方法追加文件：使用RandomAccessFile  
     */  
    public static void appendMethodA(String fileName, String content) {  
        try {  
            // 打开一个随机访问文件流，按读写方式  
            RandomAccessFile randomFile = new RandomAccessFile(fileName, "rw");  
            // 文件长度，字节数  
            long fileLength = randomFile.length();  
            //将写文件指针移到文件尾。  
            randomFile.seek(fileLength);  
            randomFile.writeBytes(content);  
            randomFile.close();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
  
    /**  
     * B方法追加文件：使用FileWriter  
     */  
    public static void appendMethodB(String fileName, String content) {  
        try {  
            //打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件  ,flase表示重头开始
            FileWriter writer = new FileWriter(fileName, false);  
            writer.write(content);  
            writer.close();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }
    
    /**  
     * 以行为单位读取文件，常用于读面向行的格式化文件  
    */  
    public static void readFileByLines(String fileName) {  
        File file = new File(fileName);  
        BufferedReader reader = null;  
        try {  
            System.out.println("以行为单位读取文件内容，一次读一整行：");  
            reader = new BufferedReader(new FileReader(file));  
            String tempString = null;  
            int line = 1;  
            // 一次读入一行，直到读入null为文件结束  
            while ((tempString = reader.readLine()) != null) {  
                // 显示行号  
               System.out.println("line " + line + ": " + tempString);  
               line++;  
            }  
            System.out.println();  
            reader.close();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            if (reader != null) {  
                try {  
                    reader.close();  
                } catch (IOException e1) {  
                }  
            }  
        }  
    }
    
    /** 
     * @method:读取文本具体某行内容 
     * @parameter 
     * @since 
     * @return 
     */  
        public static String readLineNumber(String path,int number) throws IOException {  
            long timeStart = System.currentTimeMillis();  
            File file = new File(path);//文件路径  
            FileReader fileReader = new FileReader(file);  
            LineNumberReader reader = new LineNumberReader(fileReader);  
            String txt = "";  
            int lines = 0;  
            while (txt != null) {  
                lines++;  
                txt = reader.readLine();  
                if (lines == number) {  
                    System.out.println("第" + reader.getLineNumber() + "的内容是：" + txt + "\n");  
                    long timeEnd = System.currentTimeMillis();  
                    System.out.println("总共花费：" + (timeEnd - timeStart) + "ms");  
                    break;
                }  
            }
            reader.close();  
            fileReader.close();
            return txt;
        }  
}

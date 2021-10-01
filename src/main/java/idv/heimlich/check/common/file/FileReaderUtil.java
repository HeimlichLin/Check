package idv.heimlich.check.common.file;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import idv.heimlich.check.common.code.Encoding;
import idv.heimlich.check.common.utils.TextMapper;

public class FileReaderUtil {
	
	public static List<String> readLines(File file) {
		List<String> lines = new ArrayList<String>();
		try {
			FileReader reader = new FileReader(file);
			final BufferedReader br = new BufferedReader(reader);
			String line;
			while ((line = br.readLine()) != null) {
				lines.add(line);
			}
			br.close();
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lines;
	}

	public static <T> List<T> readLines(TextMapper<T> mapper, File file) {
		final List<T> list = new ArrayList<T>();
		final List<String> lineStrings = readLines(file);

		for (String line : lineStrings) {
			try {
				T t = mapper.toVo(line);
				if (t != null) {
					list.add(t);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public static Encoding ckeckEncoding(File file) {
		int bom = 0;
		String str = " ";
		String str2 = "";
		try {
			BufferedInputStream reader = new BufferedInputStream(
					new FileInputStream(file));
			bom = (reader.read() << 8) + reader.read();
			byte bs[] = new byte[10000];
			while (str.matches("\\s+\\w*")) {
				reader.read(bs);
				str = new String(bs, Encoding.UTF8.getCode());
			}
			str2 = new String(bs, Encoding.GBK.getCode());
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Encoding code;		
		switch (bom) { // 有BOM
		case 0xefbb:
			code = Encoding.UTF8BOM;
			break;
		case 0xfffe:
			code = Encoding.UNICODE;
			break;
		case 0xfeff:
			code = Encoding.UTF16BE;
			break;
		default: // 無BOM
			if (str.length() <= str2.length()) {
				code = Encoding.UTF8;
			} else {
				code = Encoding.GBK;
			}
		}
		return code;
	}

}

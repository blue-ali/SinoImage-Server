package cn.net.sinodata.cm.mybatis.mapper.provider;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import cn.net.sinodata.cm.mybatis.model.MFile;

public class FileMapperProvider {

	public String addFiles(Map<String, List<MFile>> map){
		List<MFile> files = (List<MFile>) map.get("list");
        StringBuilder sb = new StringBuilder();  
        sb.append("INSERT INTO File (fileid, batchid, filename) values ");  
        MessageFormat mf = new MessageFormat("(#'{'list[{0}].fileId}, #'{'list[{0}].batchId}, #'{'list[{0}].fileName})");  
        for (int i = 0; i < files.size(); i++) {  
            sb.append(mf.format(new Object[]{i}));  
            if (i < files.size() - 1) {  
                sb.append(",");  
            }  
        }
        System.out.println("insert sql: " + sb.toString());
        return sb.toString();
	}
}

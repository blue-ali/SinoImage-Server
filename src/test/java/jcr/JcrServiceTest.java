package jcr;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import javax.annotation.Resource;
import javax.jcr.Node;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import base.BaseSpringTest;
import cn.net.sinodata.cm.content.IContentService;
import cn.net.sinodata.cm.content.jcr.model.JcrContent;
import cn.net.sinodata.framework.util.FileUtil;
import sun.net.www.MimeTable;

public class JcrServiceTest extends BaseSpringTest{

	
	@Resource
	private IContentService jcrService;
	
	@Before
	public void before(){
		jcrService.regist();
	}
	
	@Test
	public void testEnsureFolder(){
		jcrService.ensureFolder("/test/N1");
	}
	
	@Test
	public void testAddContent(){
		
		File file = new File("F:\\tmp\\image\\222.jpg");
		JcrContent jcrContent = new JcrContent();
		try {
			jcrContent.setData(FileUtil.file2byte(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
		jcrContent.setFileId("0005");
		MimeTable mt = MimeTable.getDefaultTable();
		String mimeType = mt.getContentTypeFor(file.getName());
		if (mimeType == null) {
			mimeType = "application/octet-stream";
		}
		jcrContent.setMimeType(mimeType);
		jcrContent.setLastModified(Calendar.getInstance());
		jcrContent.setSuffix("jpg");
		Node parentNode = jcrService.ensureFolder("/test/N1");
		jcrService.addContent("/test/N1", jcrContent);
	}
	
	@Test
	public void testNodeExist(){
		boolean flag = jcrService.isNodeExist("/999/o111/");
		Assert.assertTrue(flag);
	}
	
	@Test
	public void testGetContent(){
		JcrContent jcrContent = jcrService.getContent("/999/o111/46382dfa-fee8-4c72-9d41-606283879056");
		byte[] bytes = jcrContent.getData();
		try {
			FileUtil.byte2file(bytes, "F:\\tmp\\download\\", jcrContent.getFileName());
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(jcrContent);
	}
}

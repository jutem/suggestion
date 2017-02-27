package com.jutem.suggestion.dao;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jutem.suggestion.base.BaseTest;
import com.jutem.suggestion.model.NodeDO;

public class TireTreeDAOTest extends BaseTest{
	
	@Autowired
	private TrieTreeDAO dao;
	
	@Test
	public void mongoTest() {
		NodeDO node = new NodeDO();
		node.setId("1234");
		node.setText("test");
		dao.save(node);
		
		NodeDO result = dao.get("1");
		Assert.assertEquals("test", result.getText());
	}
}

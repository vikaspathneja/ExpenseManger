package com.project.service;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.dao.MappingDaoInterface;


@Service
@Transactional
public class MappingServiceImpl implements MappingServiceInterface {
	@Autowired
	private MappingDaoInterface daoref;

	@Override
	public HashMap finalgainloss(HttpSession hs) {
		return daoref.finalgainloss(hs);
	}

	@Override
	public void checkIncomeExpenseCategory() {
		daoref.checkIncomeExpenseCategory();
		
	}
}

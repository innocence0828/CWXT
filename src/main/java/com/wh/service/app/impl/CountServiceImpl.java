package com.wh.service.app.impl;

import com.wh.dao.CountDao;
import com.wh.entity.CountInfo;
import com.wh.service.app.CountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
//此处不再进行创建SqlSession和提交事务，都已交由spring去管理了。
public class CountServiceImpl implements CountService {
	@Resource
	private CountDao mapper;
	
	/**
	 * 加载统计数据
	 * @param
	 * @return
	 */
	@Override
	public List<CountInfo> getListCount(CountInfo countInfo1) {
		// TODO Auto-generated method stub
		List<CountInfo> countInfos1 = mapper.getListCount(countInfo1);
		List<CountInfo> countInfos2 = new ArrayList<CountInfo>();
		double douNum1 = 0;
		double douNum2 = 0;
		double douNum3 = 0;
		for(CountInfo countInfo2:countInfos1){
			countInfos2.add(countInfo2);
				douNum1+=Float.parseFloat(countInfo2.getF_Income());  
				douNum2+=Float.parseFloat(countInfo2.getF_Expense());  
				douNum3+=Float.parseFloat(countInfo2.getF_Balance());  
		}
		countInfos2.add(new CountInfo("总计", String.valueOf(new DecimalFormat("#.##").format(douNum1))
		, String.valueOf(new DecimalFormat("#.##").format(douNum2)), String.valueOf(new DecimalFormat("#.##").format(douNum3))));
		return countInfos2;
	}
	



	
	
	

}

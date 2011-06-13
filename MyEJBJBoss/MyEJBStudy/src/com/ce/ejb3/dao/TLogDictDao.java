package com.ce.ejb3.dao;

import java.util.List;

import com.ce.ejb3.model.TLogDict;

public interface TLogDictDao {
	/**
	 * 添加一个TLogDict
	 * @param logdict 人员
	 */
    public void insertTLogDict(TLogDict logdict);
    /**
     * 更新姓名
     * @param newname 新姓名
     * @param logdictid 人员ID
     */
    public void updateName(String newname, int logdictid);
    /**
     * 更新logdict对象
     * @param logdict
     */
    public void mergeTLogDict(TLogDict logdict);
    /**
     * 删除指定TLogDict
     * @param logdictid 人员ID
     */
    public void deleteTLogDict(int logdictid);
    /**
     * 获取指定TLogDict
     * @param logdictid 人员ID
     * @return
     */
    public TLogDict getTLogDictByID(int logdictid);
    /**
     * 获取全部TLogDict
     * @return
     */
    public List<TLogDict> getTLogDictList();
}

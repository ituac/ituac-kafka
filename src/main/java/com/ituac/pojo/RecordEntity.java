package com.ituac.pojo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * desp: 共用实体类、 创建时间，更新时间
 * @author ituac
 */

@Data
public class RecordEntity implements Serializable{


	private static final long serialVersionUID = -6780859057549186554L;
	
	 /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 最后修改时间
     */
    private Date gmtModified;
	

}

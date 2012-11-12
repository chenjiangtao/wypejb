package com.umpay.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "umpay.TEST_MSGENTITY")
public class MsgEntity implements Serializable {

	@Id
	private String rpid;
	private byte[] msg;
	public MsgEntity() {
	}


	public String getRpid() {
		return rpid;
	}


	public void setRpid(String rpid) {
		this.rpid = rpid;
	}

	@Lob @Basic(fetch = FetchType.EAGER)
	public byte[] getMsg() {
		return msg;
	}


	public void setMsg(byte[] msg) {
		this.msg = msg;
	}


	public int hashCode() {
		return (this.rpid == null) ? 0 : this.rpid.hashCode();
	}

	public boolean equals(Object object) {
		if (object instanceof MsgEntity) {
			final MsgEntity obj = (MsgEntity) object;
			return (this.rpid != null) ? this.rpid.equals(obj.rpid)
					: (obj.rpid == null);
		}
		return false;
	}

}

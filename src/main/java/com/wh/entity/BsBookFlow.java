package com.wh.entity;

public class BsBookFlow {
	//序号
	private String order_Id;
	private String f_Id;
	//日期
	private String f_Date;
	//备注
	private String f_Note;
	//金额
	private String f_Money;
	//名称
	private String f_Caption;
	//图片路径
	private String f_Imgurl;
	//账户类型
	private String f_Account_type;
	//消费类别
	private String f_Consume_type;
	//收入支出
	private String f_Direction_type;
	//用户
	private String f_UserId;
	//更新时间
	private String f_Update;
	//经纬度
	private String f_Position;
	//地点
	private String f_Address;
	
	
	
	public BsBookFlow() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public BsBookFlow(String fId, String fDate, String fDirectionType,
			String fNote, String fMoney, String fCaption, String fImgurl,
			String fAccountType, String fConsumeType) {
		super();
		f_Id = fId;
		f_Date = fDate;
		setF_Direction_type(fDirectionType);
		f_Note = fNote;
		f_Money = fMoney;
		f_Caption = fCaption;
		f_Imgurl = fImgurl;
		f_Account_type = fAccountType;
		f_Consume_type = fConsumeType;
	}
	

	public BsBookFlow(String fId, String fDate, String fNote, String fMoney,
			String fCaption, String fImgurl, String fAccountType,
			String fConsumeType, String fDirectionType, String fUserId) {
		super();
		f_Id = fId;
		f_Date = fDate;
		f_Note = fNote;
		f_Money = fMoney;
		f_Caption = fCaption;
		f_Imgurl = fImgurl;
		f_Account_type = fAccountType;
		f_Consume_type = fConsumeType;
		f_Direction_type = fDirectionType;
		f_UserId = fUserId;
	}
	
	
	public BsBookFlow(String f_Id, String f_Date, String f_Note,
			String f_Money, String f_Caption, String f_Imgurl,
			String f_Account_type, String f_Consume_type,
			String f_Direction_type, String f_UserId, String f_Update,
			String f_Position,String f_Address) {
		super();
		this.f_Id = f_Id;
		this.f_Date = f_Date;
		this.f_Note = f_Note;
		this.f_Money = f_Money;
		this.f_Caption = f_Caption;
		this.f_Imgurl = f_Imgurl;
		this.f_Account_type = f_Account_type;
		this.f_Consume_type = f_Consume_type;
		this.f_Direction_type = f_Direction_type;
		this.f_UserId = f_UserId;
		this.f_Update = f_Update;
		this.f_Position = f_Position;
		this.f_Address = f_Address;
	}

	public String getF_Id() {
		return f_Id;
	}
	public void setF_Id(String fId) {
		f_Id = fId;
	}
	public String getF_Date() {
		return f_Date;
	}
	public void setF_Date(String fDate) {
		f_Date = fDate;
	}

	public String getF_Note() {
		return f_Note;
	}
	public void setF_Note(String fNote) {
		f_Note = fNote;
	}
	public String getF_Money() {
		return f_Money;
	}
	public void setF_Money(String fMoney) {
		f_Money = fMoney;
	}
	public String getF_Caption() {
		return f_Caption;
	}
	public void setF_Caption(String fCaption) {
		f_Caption = fCaption;
	}
	public String getF_Imgurl() {
		return f_Imgurl;
	}
	public void setF_Imgurl(String fImgurl) {
		f_Imgurl = fImgurl;
	}
	public String getF_Account_type() {
		return f_Account_type;
	}
	public void setF_Account_type(String fAccountType) {
		f_Account_type = fAccountType;
	}
	public String getF_Consume_type() {
		return f_Consume_type;
	}
	public void setF_Consume_type(String fConsumeType) {
		f_Consume_type = fConsumeType;
	}

	public void setF_Direction_type(String f_Direction_type) {
		this.f_Direction_type = f_Direction_type;
	}

	public String getF_Direction_type() {
		return f_Direction_type;
	}

	public void setF_UserId(String f_UserId) {
		this.f_UserId = f_UserId;
	}

	public String getF_UserId() {
		return f_UserId;
	}

	public String getF_Update() {
		return f_Update;
	}

	public void setF_Update(String f_Update) {
		this.f_Update = f_Update;
	}

	public String getOrder_Id() {
		return order_Id;
	}

	public void setOrder_Id(String order_Id) {
		this.order_Id = order_Id;
	}

	public String getF_Position() {
		return f_Position;
	}

	public void setF_Position(String f_Position) {
		this.f_Position = f_Position;
	}

	public String getF_Address() {
		return f_Address;
	}

	public void setF_Address(String f_Address) {
		this.f_Address = f_Address;
	}
	

}

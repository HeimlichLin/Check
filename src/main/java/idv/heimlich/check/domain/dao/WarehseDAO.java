package idv.heimlich.check.domain.dao;
//package idv.heimlich.BaseProject.domain.dao; 
//
//import idv.heimlich.BaseProject.common.dao.GeneralDAO;
//import idv.heimlich.BaseProject.common.dao.IConverter;
//import idv.heimlich.BaseProject.common.dao.RowMap;
//import idv.heimlich.BaseProject.common.db.sql.SqlWhere;
//import idv.heimlich.BaseProject.common.utils.CommonUtils;
//import idv.heimlich.BaseProject.domain.bean.WarehseDo;
// 
//public class WarehseDAO extends GeneralDAO<WarehseDo> { 
//	public static final WarehseDAO INSTANCE = new WarehseDAO(); 
//	public static final String TABLENAME = "WAREHSE"; 
//
//	public WarehseDAO() { 
//		super(TABLENAME); 
//	} 
// 
//	protected static IConverter<WarehseDo> CONVERTER = new IConverter<WarehseDo>() { 
//
//		@Override
//		public WarehseDo convert(RowMap rapMap) {
//			final WarehseDo warehseDo = new WarehseDo(); 
//			warehseDo.setPostcode(rapMap.getString(WarehseDo.COLUMNS.POSTCODE.name())); 
//			warehseDo.setBondno(rapMap.getString(WarehseDo.COLUMNS.BONDNO.name())); 
//			warehseDo.setBondban(rapMap.getString(WarehseDo.COLUMNS.BONDBAN.name())); 
//			warehseDo.setBondname(rapMap.getString(WarehseDo.COLUMNS.BONDNAME.name())); 
//			warehseDo.setSpecialst(rapMap.getString(WarehseDo.COLUMNS.SPECIALST.name())); 
//			warehseDo.setBondadd(rapMap.getString(WarehseDo.COLUMNS.BONDADD.name())); 
//			warehseDo.setBondid(rapMap.getString(WarehseDo.COLUMNS.BONDID.name())); 
//			warehseDo.setBondpw(rapMap.getString(WarehseDo.COLUMNS.BONDPW.name())); 
//			warehseDo.setAuthority(rapMap.getString(WarehseDo.COLUMNS.AUTHORITY.name())); 
//			warehseDo.setRcvid(rapMap.getString(WarehseDo.COLUMNS.RCVID.name())); 
//			warehseDo.setSepid(rapMap.getString(WarehseDo.COLUMNS.SEPID.name())); 
//			warehseDo.setCustomsoffice(rapMap.getString(WarehseDo.COLUMNS.CUSTOMSOFFICE.name())); 
//			warehseDo.setRecvFlag(rapMap.getString(WarehseDo.COLUMNS.RECV_FLAG.name())); 
//			warehseDo.setBondtype(rapMap.getString(WarehseDo.COLUMNS.BONDTYPE.name())); 
//			warehseDo.setStatus(rapMap.getString(WarehseDo.COLUMNS.STATUS.name())); 
//			warehseDo.setActivedate(CommonUtils.formObj(rapMap.getString(WarehseDo.COLUMNS.ACTIVEDATE.name()))); 
//			warehseDo.setEnddate(CommonUtils.formObj(rapMap.getString(WarehseDo.COLUMNS.ENDDATE.name()))); 
//			warehseDo.setChargedate(CommonUtils.formObj(rapMap.getString(WarehseDo.COLUMNS.CHARGEDATE.name()))); 
//			warehseDo.setTel(rapMap.getString(WarehseDo.COLUMNS.TEL.name())); 
//			warehseDo.setFax(rapMap.getString(WarehseDo.COLUMNS.FAX.name())); 
//			warehseDo.setCofficer(rapMap.getString(WarehseDo.COLUMNS.COFFICER.name())); 
//			warehseDo.setCustomFlag(rapMap.getString(WarehseDo.COLUMNS.CUSTOM_FLAG.name())); 
//			warehseDo.setAutoconfirm(rapMap.getString(WarehseDo.COLUMNS.AUTOCONFIRM.name())); 
//			return warehseDo;
//		}
//
//		@Override
//		public RowMap toRowMap(WarehseDo warehseDo) {
//			final RowMap rapMap = new RowMap(); 
//			rapMap.setValue(WarehseDo.COLUMNS.POSTCODE.name(), warehseDo.getPostcode()); 
//			rapMap.setValue(WarehseDo.COLUMNS.BONDNO.name(), warehseDo.getBondno()); 
//			rapMap.setValue(WarehseDo.COLUMNS.BONDBAN.name(), warehseDo.getBondban()); 
//			rapMap.setValue(WarehseDo.COLUMNS.BONDNAME.name(), warehseDo.getBondname()); 
//			rapMap.setValue(WarehseDo.COLUMNS.SPECIALST.name(), warehseDo.getSpecialst()); 
//			rapMap.setValue(WarehseDo.COLUMNS.BONDADD.name(), warehseDo.getBondadd()); 
//			rapMap.setValue(WarehseDo.COLUMNS.BONDID.name(), warehseDo.getBondid()); 
//			rapMap.setValue(WarehseDo.COLUMNS.BONDPW.name(), warehseDo.getBondpw()); 
//			rapMap.setValue(WarehseDo.COLUMNS.AUTHORITY.name(), warehseDo.getAuthority()); 
//			rapMap.setValue(WarehseDo.COLUMNS.RCVID.name(), warehseDo.getRcvid()); 
//			rapMap.setValue(WarehseDo.COLUMNS.SEPID.name(), warehseDo.getSepid()); 
//			rapMap.setValue(WarehseDo.COLUMNS.CUSTOMSOFFICE.name(), warehseDo.getCustomsoffice()); 
//			rapMap.setValue(WarehseDo.COLUMNS.RECV_FLAG.name(), warehseDo.getRecvFlag()); 
//			rapMap.setValue(WarehseDo.COLUMNS.BONDTYPE.name(), warehseDo.getBondtype()); 
//			rapMap.setValue(WarehseDo.COLUMNS.STATUS.name(), warehseDo.getStatus()); 
//			rapMap.setValue(WarehseDo.COLUMNS.ACTIVEDATE.name(), warehseDo.getActivedate()); 
//			rapMap.setValue(WarehseDo.COLUMNS.ENDDATE.name(), warehseDo.getEnddate()); 
//			rapMap.setValue(WarehseDo.COLUMNS.CHARGEDATE.name(), warehseDo.getChargedate()); 
//			rapMap.setValue(WarehseDo.COLUMNS.TEL.name(), warehseDo.getTel()); 
//			rapMap.setValue(WarehseDo.COLUMNS.FAX.name(), warehseDo.getFax()); 
//			rapMap.setValue(WarehseDo.COLUMNS.COFFICER.name(), warehseDo.getCofficer()); 
//			rapMap.setValue(WarehseDo.COLUMNS.CUSTOM_FLAG.name(), warehseDo.getCustomFlag()); 
//			rapMap.setValue(WarehseDo.COLUMNS.AUTOCONFIRM.name(), warehseDo.getAutoconfirm()); 
//			return rapMap; 
//		} 
// 
//	}; 
// 
//	public IConverter<WarehseDo> getConverter() { 
//		return CONVERTER; 
//	} 
// 
//	@Override 
//	public SqlWhere getPkSqlWhere(WarehseDo po) { 
//		SqlWhere sqlWhere = new SqlWhere(); 
//		sqlWhere.add(WarehseDo.COLUMNS.BONDNO.name(), po.getBondno()); 
//		sqlWhere.add(WarehseDo.COLUMNS.BONDID.name(), po.getBondid()); 
//		return sqlWhere; 
//	} 
// 
//} 

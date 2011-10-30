package biz.wittkemper.stationmanager.data.dao;

import biz.wittkemper.stationmanager.data.entity.Parameter;

public class ParameterDAOImpl extends AbstractDAOImpl<Parameter, Long>
		implements ParameterDAO {

	@Override
	protected Class<Parameter> getDomainClass() {
		return Parameter.class;
	}

}

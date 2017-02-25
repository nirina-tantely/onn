package org.onn.webportal.infra.repository;

import java.util.List;
import org.onn.webportal.domain.model.ActiviteMetadata;
import org.onn.webportal.domain.model.IndicateurONG;
import org.onn.webportal.domain.model.IndicateurSMS;

public interface MetadataRepo {

	List<ActiviteMetadata> getActiviteMetadata();

	List<IndicateurONG> getIndicateurONGMetadata();

	List<IndicateurSMS> getIndicateurSMSMetadata();
}

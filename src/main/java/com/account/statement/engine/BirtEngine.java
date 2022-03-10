package com.account.statement.engine;

import org.eclipse.birt.core.framework.Platform;
import org.eclipse.birt.report.engine.api.EngineConfig;
import org.eclipse.birt.report.engine.api.EngineException;
import org.eclipse.birt.report.engine.api.IReportEngine;
import org.eclipse.birt.report.engine.api.IReportEngineFactory;
import org.eclipse.birt.report.engine.api.IReportRunnable;
import org.eclipse.birt.report.engine.api.IRunAndRenderTask;
import org.eclipse.birt.report.engine.api.PDFRenderOption;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.account.statement.constants.KeyConstants;
import com.account.statement.dtos.ResponseDto;
import com.account.statement.utils.BirtParameters;
@Service
public class BirtEngine 
{
	@Value("${file.path}")
	private String filePath;
	@Value("${birt.report.template}")
	private String birtReportTemplate;
	public static final String PDF_EXTENSION = ".pdf";
	
	public ResponseDto birtReportGenerate(int randomReferenceNum, String fileNameWithTimeStamp, String jsonValue, String templateId, String extension)throws EngineException {
		ResponseDto resDto = new ResponseDto();
		IReportEngine birtEngine = null;
		EngineConfig birtConfig = null;
		IReportRunnable templateDesign = null;
		try {
			birtConfig = new EngineConfig();
			Platform.startup(birtConfig);
			IReportEngineFactory factory = (IReportEngineFactory) Platform.createFactoryObject(IReportEngineFactory.EXTENSION_REPORT_ENGINE_FACTORY);
			birtEngine = factory.createReportEngine(birtConfig);
			templateDesign = birtEngine.openReportDesign(birtReportTemplate+templateId+KeyConstants.BIRT_TEMPLATE_EXTENSION);
			IRunAndRenderTask task = birtEngine.createRunAndRenderTask(templateDesign);
			PDFRenderOption options = new PDFRenderOption();
			//Setting Parameter in Birt Template
			task = BirtParameters.birtParameterSetting(randomReferenceNum,task,jsonValue,templateId);
			options.setOutputFileName(filePath + fileNameWithTimeStamp + "."+extension);
			options.setOutputFormat(extension);
			task.setRenderOption(options);
			task.run();
			task.close();
			birtEngine.destroy();
			resDto.setIsError(0);
			return resDto;
		} catch (Exception ex) {
			ex.printStackTrace();
			resDto.setErrorMessage(ex.getMessage());
			resDto.setIsError(1);
			return resDto;
		} finally {
			Platform.shutdown();
		}
	}

}

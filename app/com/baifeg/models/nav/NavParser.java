package com.baifeg.models.nav;

import java.io.File;
import java.util.List;

public interface NavParser
{
	/**
	 * @param navDir
	 * @return
	 * @throws ParseException
	 */
	public Nav parse(String navDir) throws ParseException;

	/**
	 * @param navConfList
	 * @return
	 * @throws ParseException
	 */
	public Nav parse(List<File> navConfList) throws ParseException;

	/**
	 * @param navConf
	 * @return
	 * @throws ParseException
	 */
	public Nav parse(File navConf) throws ParseException;
}

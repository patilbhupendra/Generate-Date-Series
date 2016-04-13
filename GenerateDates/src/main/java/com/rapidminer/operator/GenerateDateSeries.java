/*
 * RapidMiner GmbH
 * 
 * Copyright (C) 2016-2016 by RapidMiner GmbH and the contributors
 * 
 * Complete list of developers available at our web site:
 * 
 * www.rapidminer.com
 * 
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU Affero General Public License as published by the Free Software Foundation, either version 3
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with this program.
 * If not, see http://www.gnu.org/licenses/.
 */
package com.rapidminer.operator;



import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.rapidminer.example.ExampleSet;
import com.rapidminer.operator.io.AbstractExampleSource;


import com.rapidminer.parameter.ParameterType;
import com.rapidminer.parameter.ParameterTypeCategory;
import com.rapidminer.parameter.ParameterTypeString;
import com.rapidminer.tools.Ontology;
import com.rapidminer.tools.expression.internal.function.statistical.Random;
import com.rapidminer.example.Attribute;
import com.rapidminer.example.table.AttributeFactory;
import com.rapidminer.example.table.DataRow;
import com.rapidminer.example.table.DoubleArrayDataRow;
import com.rapidminer.example.table.MemoryExampleTable;

import org.jfree.data.time.Millisecond;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.Minutes;
import org.joda.time.Months;
import org.joda.time.Seconds;
import org.joda.time.Years;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
/**
 * 
 * @author Bhupendra Patil
 *
 */
public class GenerateDateSeries extends AbstractExampleSource {

	private static final String STARTDATE = "startdate";
	private static final String STARTDATEFORMAT = "startdateformat";
	private static final String ENDDATE = "enddate";
	private static final String ENDDATEFORMAT = "enddateformat";
	private static final String INTERVAL = "interval";
	private static final String PARAMETER_INTERVALTYPE = "intervaltype";
	private static final String[] INTERVALTYPE = new String[] { "YEAR", "MONTH", "DAY", "HOUR", "MINUTE", "SECOND", "MILLISECOND"};
	private static final int INTERVALTYPE_INDEX = 0;
		
	/**
	 * 
	 * @param description this is description
	 */
	public GenerateDateSeries(OperatorDescription description) {
		super(description);
		// TODO Auto-generated constructor stub
	}

	
	
	@Override
	public ExampleSet createExampleSet() throws OperatorException {
		// TODO Auto-generated method stub
		
		// init
		DateTimeFormatter dtf = DateTimeFormat.forPattern(getParameterAsString(STARTDATEFORMAT));
		// Parsing the date
		DateTime startTime = dtf.parseDateTime(getParameterAsString(STARTDATE));
	
		 dtf = DateTimeFormat.forPattern(getParameterAsString(ENDDATEFORMAT));
		// Parsing the date
		DateTime endTime = dtf.parseDateTime(getParameterAsString(ENDDATE));
		
		//int numberOfExamples =
		
		//in hindsight you dont need to calculate number of rows
		//	long numberOfExamples = 	calculateNumberOfExample(startTime, endTime);
		
		// create table
			List<Attribute> attributes = new ArrayList<Attribute>();
		
			Attribute dateattribute = AttributeFactory.createAttribute("Date", Ontology.DATE_TIME);
			attributes.add(dateattribute);
			
			MemoryExampleTable table = new MemoryExampleTable(attributes);
			double[] values = new double[1];
			int increment = getParameterAsInt(INTERVAL);
			
				
				 switch (getParameterAsString(PARAMETER_INTERVALTYPE))
				 {
				 
				 case "YEAR":
					 for (DateTime n = startTime; n.isBefore(endTime); n= n.plusYears(increment)) {
					 values[0] = n;
					 	table.addDataRow(new DoubleArrayDataRow(values));
					 }
					 break;
					 //just a new comment
				 case "MONTH":
					 for (DateTime n = startTime; n.isBefore(endTime); n= n.plusMonths(increment)) {
						 values[0] = n;
						 table.addDataRow(new DoubleArrayDataRow(values));
						 }
					 break;
				 	 
				 case "DAY" : 
					 for (DateTime n = startTime; n.isBefore(endTime); n= n.plusDays(increment)) {
						 values[0] = n;
						 table.addDataRow(new DoubleArrayDataRow(values));
						 }
					 
					 break;
					 
				 case "HOUR":
					 for (DateTime n = startTime; n.isBefore(endTime); n= n.plusHours(increment)) {
						 values[0] = n;
						 table.addDataRow(new DoubleArrayDataRow(values));
						 }
						 break;
				
				 case "MINUTE" :
					 for (DateTime n = startTime; n.isBefore(endTime); n= n.plusMinutes(increment)) {
						 values[0] = n;
						 table.addDataRow(new DoubleArrayDataRow(values));
						 }
						 break;
				 case "SECOND" :
					 for (DateTime n = startTime; n.isBefore(endTime); n= n.plusSeconds(increment)) {
						 values[0] = n;
						 table.addDataRow(new DoubleArrayDataRow(values));
						 }
						 break;
				 case "MILLISECOND": 
					 for (DateTime n = startTime; n.isBefore(endTime); n= n.plusMillis(increment)) {
						 values[0] = n;
						 table.addDataRow(new DoubleArrayDataRow(values));
						 }
						 break;
				 default: 
					break;
				 }
				
			
			
		//numberOfExamples++;
		
		return table.createExampleSet();
	}



	private long calculateNumberOfExample(DateTime startTime,DateTime endTime)   {
		// TODO Auto-generated method stub
		long valuetoReturn = 0;		 
		try
		{
			
			
			
			
		//getParameterAsString(key)
				
			 switch (getParameterAsString(PARAMETER_INTERVALTYPE))
			 {
			 
			 case "YEAR":
				 Years year = Years.yearsBetween(startTime, endTime);
				 valuetoReturn = year.getYears()/getParameterAsInt(INTERVAL);
				 break;
			 case "MONTH":
				 Months month = Months.monthsBetween(startTime, endTime);
				 valuetoReturn = month.getMonths()/getParameterAsInt(INTERVAL);
				 break;
			 	 
			 case "DAY" : 
				 Days days = Days.daysBetween(startTime, endTime);
				 valuetoReturn = days.getDays()/getParameterAsInt(INTERVAL);
				 
				 break;
				 
			 case "HOUR":
				 Hours hours = Hours.hoursBetween(startTime, endTime);
				 valuetoReturn = hours.getHours()/getParameterAsInt(INTERVAL);
					 break;
			
			 case "MINUTE" :
				 Minutes minute = Minutes.minutesBetween(startTime, endTime);
				 valuetoReturn = minute.getMinutes()/getParameterAsInt(INTERVAL);
				 break;
			 case "SECOND" :
				 Seconds second = Seconds.secondsBetween(startTime, endTime);
				 valuetoReturn = second.getSeconds()/getParameterAsInt(INTERVAL);
				 break;
			 case "MILLISECOND": 
				// Milliseconds millisecond = milli
				 long milli = endTime.getMillis()-  startTime.getMillis();
				 valuetoReturn = milli/getParameterAsInt(INTERVAL);
				 
				 break;
			 default: 
				 valuetoReturn = 0;
			 }
				 
		}
		catch(Exception e)
		{
			valuetoReturn = 0;
		}
		 
		
		
		
		return valuetoReturn;
	}
	/*
     * (non-Javadoc)
     * 
     * @see com.rapidminer.operator.io.AbstractWriter#getParameterTypes()
     */
    @Override 
    public List<ParameterType> getParameterTypes() {

    	
    	
        final List<ParameterType> types = new LinkedList<ParameterType>();

        types.add(new ParameterTypeString(STARTDATE, 
                "start date", false));

        types.add(new ParameterTypeString(STARTDATEFORMAT, 
                "start date format", false));


        types.add(new ParameterTypeString(ENDDATE, 
                "end date", false));


        types.add(new ParameterTypeString(ENDDATEFORMAT, 
                "end date format", false));


        types.add(new ParameterTypeString(INTERVAL, 
                "interval", false));

        types.add(new ParameterTypeCategory(PARAMETER_INTERVALTYPE, 
                "interval type", INTERVALTYPE, INTERVALTYPE_INDEX, false));

        types.addAll(super.getParameterTypes());

        return types;
    }
}

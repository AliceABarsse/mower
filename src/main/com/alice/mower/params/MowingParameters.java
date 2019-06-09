/**
 * 
 */
package com.alice.mower.params;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.alice.mower.environment.Coordinate;

/**
 * @author alicebarsse
 *
 *         This class handles loading parameters for the project.
 * 
 *         Format in input file:
 * 
 *         first line : "X Y" : dimensions of lawn
 * 
 *         second line: "X Y O": coordinates and orientation of mower 1
 * 
 *         third line: [M]+ : list of movements to accomplish for mower 1
 * 
 *         repeat second and third codes for additional mowers
 *
 */
public final class MowingParameters {

	public static final String LINE_INNER_SEPARATOR = " ";
	private static final String DEFAULT_INPUT_TXT = "resources/input.txt";

	/**
	 * Limit how many lines of instructions we read, in case things get out of
	 * hand!
	 */
	public static final long MAX_FILE_LINE_COUNT = 100L;

	/**
	 * Limit maximum size of lawn grid to avoid problems
	 */
	public static final int MAX_LAWN_SIZE = 1000;

	private Coordinate lawnUpperRightCorner;

	private final List<MowerParameters> mowerParams;

	public MowingParameters() {
		mowerParams = new ArrayList<>();
	}

	/**
	 * Read input file for parameters.
	 * 
	 * @param filePath
	 *            May be null, in which case we use default file as input
	 *            instead.
	 * @throws IllegalArgumentException
	 *             if input file cannot be read or contents does not have
	 *             expected format
	 */
	public void init(String filePath) {

		List<String> list = null;
		
		try (InputStream systemResourceAsStream = ClassLoader.getSystemResourceAsStream(DEFAULT_INPUT_TXT);
				InputStreamReader in = new InputStreamReader(systemResourceAsStream);
				BufferedReader bufferedReader = new BufferedReader(in);
				Stream<String> stream = (filePath == null ? bufferedReader.lines() : Files.lines(Paths.get(filePath)))) {
			// limit lines read, filter empty lines
			list = stream.limit(MAX_FILE_LINE_COUNT).filter(line -> !line.isEmpty()).collect(Collectors.toList());

		} catch (NullPointerException | IOException e) {
			throw new IllegalArgumentException("Input file " + filePath + " cannot be read:" + e.getMessage());
		} 

		String mowerParams1 = null, mowerParams2 = null;
		for (String line : list) {
			if (lawnUpperRightCorner == null) {
				lawnUpperRightCorner = validateLawnParam(line);
				continue;
			}
			if (mowerParams1 == null) {
				mowerParams1 = line;
				continue;
			}
			if (mowerParams2 == null) {
				mowerParams2 = line;
				MowerParameters mp = new MowerParameters(mowerParams1, mowerParams2);
				mp.validateMowerParams(lawnUpperRightCorner);
				mowerParams.add(mp);

				// re-init
				mowerParams1 = null;
				mowerParams2 = null;
				continue;
			}
		}
		// System.out.println("lawn params: " + lawnUpperRightCorner);
		// System.out.println("mower params: " + mowerParams);
	}

	public int mowerCount() {
		return mowerParams.size();
	}

	/**
	 * Throw IllegalArgumentException if the input String does not match
	 * expected format.
	 * 
	 * @param lawnInfoString
	 *            should be in format two integers separated by single space
	 * @return Coordinate read from given string
	 * @throws IllegalArgumentException
	 *             if the input does not have expected format or value exceeds
	 *             program bounds
	 */
	public Coordinate validateLawnParam(String lawnInfoString) {

		String[] split = lawnInfoString.split(LINE_INNER_SEPARATOR);
		if (split.length != 2) {
			throw new IllegalArgumentException("Lawn info in file cannot be parsed <<" + lawnInfoString + ">>");
		}
		try {
			int xCoord = Integer.parseInt(split[0]);
			int yCoord = Integer.parseInt(split[1]);
			if (xCoord > MAX_LAWN_SIZE || yCoord > MAX_LAWN_SIZE) {
				throw new IllegalArgumentException(
						"Lawn dimensions (" + xCoord + "," + yCoord + ") exceed maximum <<" + MAX_LAWN_SIZE + ">>");
			}
			return new Coordinate(xCoord, yCoord);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(
					"Lawn info in file cannot be parsed as integers <<" + lawnInfoString + ">>");
		}
	}

	public Coordinate getLawnUpperRightCorner() {
		return lawnUpperRightCorner;
	}

	public List<MowerParameters> getMowerParameters() {
		return mowerParams;
	}

}

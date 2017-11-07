package de.ipk_gatersleben.bit.bi.bridge.brapicomp.testing.reports;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.ipk_gatersleben.bit.bi.bridge.brapicomp.utils.RunnerService;

/**
 * Stores variables to be used during testing. Variables can be used in URLs (Using {} braces).
 * Variables can also be used to compare against results from the query
 */
public class VariableStorage {
	private static final Logger LOGGER = Logger.getLogger(VariableStorage.class.getName());
	private HashMap<String, JsonNode> variables;
	private HashMap<String, String> variablesAsString;
	
	public VariableStorage() {
		this.variables = new HashMap<String, JsonNode>();
		this.variablesAsString = new HashMap<String, String>();
	}

	/**
	 * Initialise a VariableStorage with a baseurl.
	 * @param baseUrl Base Url. Will be included in the storage as a string.
	 */
	public VariableStorage(String baseUrl) {
		this();
		try {
			setVariable("baseurl", "\"" + baseUrl + "\"");
		} catch (IOException e) {
			LOGGER.warning("Unable to read {baseurl}: " + baseUrl + ". Make sure it is a String");
			e.printStackTrace();
		}
	}

	/**
	 * @return variables HashMap that stores the variables as JsonNode, 
	 * this means Json objects can also be stored, used and compared.
	 */
	public HashMap<String, JsonNode> getVariables() {
		return variables;
	}
	
	/**
	 * @return variablesAsString HashMap which contains variables in a string format with no quotes,
	 * in order to be used when replacing variables in urls. Otherwise the string is substituted with quotes.
	 */
	public HashMap<String, String> getVariablesAsString() {
		return variablesAsString;
	}
	
	public void setVariable(String key, JsonNode value) {
		this.variables.put(key, value);
		this.variablesAsString.put(key, value.toString().replaceAll("\"", ""));
	}
	
	public JsonNode getVariable(String key) {
		return this.variables.get(key);
	}
	public String getVariableAsString(String key) {
		return this.variablesAsString.get(key);
	}

	/**
	 * This method tries to convert a string into JsonNode before storing it.
	 * @param key Key to use when storing and retrieving the value.
	 * @param value String to be converted into JsonTree and stored.
	 * @throws IOException When having problems converting the string to Json
	 */
	public void setVariable(String key, String value) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jn = mapper.readTree(value);
		setVariable(key, jn);
	}
}

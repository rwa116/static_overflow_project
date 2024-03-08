package static_overflow_project;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import ghidra.program.model.listing.Function;
import ghidra.program.model.listing.FunctionIterator;
import ghidra.program.model.listing.Parameter;
import ghidra.program.model.symbol.Reference;
import ghidra.program.model.symbol.ReferenceIterator;

public class SinkHandler {
	private HashSet<Function> functionSet = new HashSet<Function>();
	private static HashSet<String> knownSinks = new HashSet<String>();
	
	static { // All supported functions
		knownSinks.add("strcpy");
		knownSinks.add("strncpy");
		knownSinks.add("strlcpy");
		knownSinks.add("strcat");
		knownSinks.add("strncat");
		knownSinks.add("strlcat");
		knownSinks.add("wcscpy");
		knownSinks.add("wcsncpy");
		knownSinks.add("wcscat");
		knownSinks.add("wcsncat");
		knownSinks.add("memcpy");
		knownSinks.add("memmove");
		knownSinks.add("gets");
		knownSinks.add("fgets");
	}
	
	public HashSet<Sink> FindSinks() {
		HashSet<Sink> discoveredSinks = new HashSet<Sink>();
		HashSet<Function> duplicates = new HashSet<Function>();
		FunctionIterator functions = Analyzer.Listing.getFunctions(true);
		
		while(functions.hasNext()) {
			Function function = functions.next();
			if(knownSinks.contains(function.getName()) && !duplicates.contains(function)) {
				duplicates.add(function);
				ReferenceIterator references = Analyzer.ReferenceManager.getReferencesTo(function.getEntryPoint());
				
				for(Reference reference : references) {
					discoveredSinks.add(new Sink(function.getName(), reference.getToAddress(), 
							reference.getFromAddress(), function.getParameters()));
				}
			}
		}
		
		return discoveredSinks;
	}
	
	public void FindOverflow(Sink sink) {
		// TODO: Takes in a Sink object and writes overflow details to a file
	}
	
}
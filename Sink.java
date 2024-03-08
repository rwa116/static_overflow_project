package static_overflow_project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import ghidra.program.model.address.*;
import ghidra.program.model.listing.Parameter;

public class Sink {
	public String name;
	public Address location;
	public Address refLocation;
	public List<Parameter> arguments;
	public List<Source> sources = new ArrayList<Source>();
	
	public Sink(String name, Address location, Address refLocation, Parameter[] arguments) {
		this.name = name;
		this.location = location;
		this.refLocation = refLocation;
		this.arguments = Arrays.asList(arguments);
	}
	
	public void SetSources(List<Source> newSources) {
		this.sources.addAll(newSources);
	}
	
	public void CalculateOverflow() {
		// TODO: write to file about possible overflows
		if(arguments.size() == 2) { // 2 argument sink, has only dest and src char*
			Parameter first = arguments.get(0);
		}
	}
}

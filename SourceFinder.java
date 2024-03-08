package static_overflow_project;

import ghidra.program.model.address.Address;
import ghidra.program.model.listing.Parameter;
import ghidra.program.model.listing.Function;
import ghidra.program.model.listing.Instruction;

import java.util.List;

public class SourceFinder {
	
	public void FindSources(Address entryAddress, List<Parameter> arguments) {
		Function function = Analyzer.Listing.getFunctionContaining(entryAddress);
//		while(entryAddress.compareTo(function.getBody().getMaxAddress()) <= 0) {
//			Instruction currentInstruction = Analyzer.Listing.getInstructionAt(entryAddress); 
//		}
		
		// TODO: Search upwards
		Analyzer.Listing.getCodeUnitBefore(entryAddress);
	}
}

package static_overflow_project;

import ghidra.program.model.address.Address;
import ghidra.program.model.listing.Parameter;
import ghidra.program.model.listing.Function;
import ghidra.program.model.listing.Instruction;
import ghidra.program.flatapi.*;

import java.util.List;

public class SourceFinder {
	
	public void FindSources(Address entryAddress, List<Parameter> arguments) {
		Function function = Analyzer.FlatApi.getFunctionBefore(entryAddress);
		System.out.println("FunctionBefore name = " + function.getName());
		Function nextFunction = Analyzer.FlatApi.getFunctionAfter(function.getEntryPoint());
		arguments.get(0).getRegister();
//		while(entryAddress.compareTo(function.getBody().getMaxAddress()) <= 0) {
//			Instruction currentInstruction = Analyzer.Listing.getInstructionAt(entryAddress); 
//		}
		
		// TODO: Search upwards
		Address newAddress = entryAddress;
		while(newAddress != function.getEntryPoint()) {
			Instruction prevInstruction = Analyzer.Listing.getInstructionBefore(newAddress);
			
			if(prevInstruction == null) {
				return;
			}
			
			String operation = Helper.getInstructionString(prevInstruction);
			List<String> operands = Helper.getInstructionOperands(prevInstruction);
			System.out.println("Operation = " + operation + ", operand 0 = " + operands.get(0));
			
			newAddress = prevInstruction.getMinAddress();
		}
	}
}

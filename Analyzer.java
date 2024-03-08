package static_overflow_project;

import java.util.ArrayList;

/*
 * ./analyzeHeadless /home/ryan/code/cmpt_479/ ghidra_prac -process CWE121_Stack_Based_Buffer_Overflow__char_type_overrun_memcpy_01.out -postscript static_overflow_project.Analyzer
 */

// ./analyzeHeadless /home/ryan/code/cmpt_479/ ghidra_prac -process strings -postscript overflow_package.Analyzer
import java.util.HashSet;

import ghidra.app.decompiler.DecompInterface;
import ghidra.app.decompiler.DecompileOptions;
import ghidra.app.script.GhidraScript;
import ghidra.program.model.address.*;
import ghidra.program.model.symbol.*;
import ghidra.program.flatapi.*;
import ghidra.program.flatapi.FlatProgramAPI;
import ghidra.program.model.listing.Function;
import ghidra.program.model.listing.Listing;
import ghidra.program.model.listing.Parameter;

public class Analyzer extends GhidraScript{
	public DecompInterface decomp;
	
	public static ArrayList<Source> GlobalSources;
	public static HashSet<Function> FunctionsUsed;
	public static Boolean SecondPass = false;
	public static Boolean OutputFile = false;
	public static Integer FindSourceLimit = 5;
	public static Listing Listing;
	public static AddressFactory AddressFactory;
	public static ReferenceManager ReferenceManager;
	public static String ProgramName;
	public static FlatProgramAPI FlatApi;
	
	private void decompilerSetup() {
		decomp = new DecompInterface();
		
		DecompileOptions options;
		options = new DecompileOptions();
		
		decomp.setOptions(options);
		decomp.toggleCCode(false);
		decomp.toggleSyntaxTree(true);
		decomp.setSimplificationStyle("decompile");
		decomp.openProgram(currentProgram);
	}
	
	private void globalSetup() {
		Listing = currentProgram.getListing();
		AddressFactory = currentProgram.getAddressFactory();
		ReferenceManager = currentProgram.getReferenceManager();
		ProgramName = currentProgram.getName();
		FlatApi = new FlatProgramAPI(currentProgram);
	}

	@Override
	protected void run() throws Exception {
		decompilerSetup();
		globalSetup();
		HashSet<Sink> discoveredSinks;
		
		SinkHandler sinkHandler = new SinkHandler();
		SourceHandler sourceHandler = new SourceHandler();
		
		discoveredSinks = sinkHandler.FindSinks();
		
		
		System.out.println("Hello!");
		
		for(Sink dis : discoveredSinks) {
			System.out.println("Discovered sink: ");
			System.out.println(dis.name + " " + dis.location + " " + dis.refLocation);
			for(Parameter param : dis.arguments) {
				System.out.print(param);
				System.out.print(", ");
				System.out.print(param.getDataType() + " " + param.getOrdinal());
				System.out.print(", ");
				System.out.print(param.getRegister());
				System.out.print(", ");
			}
			System.out.println("");
			
			sourceHandler.GetSources(dis);
			break;
		}
		
	}


}

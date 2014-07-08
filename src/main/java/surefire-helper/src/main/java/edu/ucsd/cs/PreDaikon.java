package edu.ucsd.cs;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PreDaikon {
	
	private static final SimpleLog basic = new SimpleLog (false);
	public static boolean debug = false;
	public static final String synopsis = "daikon.Chicory [options] target [target-args]";
	@Option ("Depth to examine structure components")
    public static int nesting_depth = 2;
	@Option ("File in which to put dtrace output")
    public static /*@MonotonicNonNull*/ File dtrace_file = null;
	@Option ("Path to the Chicory agent jar file")
	public static /*@MonotonicNonNull*/ File premain = null;
	private static final String traceLimTermString = "DTRACELIMITTERMINATE";
	private static final String traceLimString = "DTRACELIMIT";
	@Option ("Size of the heap for the target program, and for Daikon if it is run")
	public static String heap_size = "1000m";
	
	public static void outputDaikonCmd(String[] args) {
		// Parse our arguments
	    Options options = new Options (synopsis, PreDaikon.class);
	    options.parse_options_after_arg (false);
	    String[] target_args = options.parse_or_usage (args);
	    boolean ok = check_args (options, target_args);
	    if (!ok)
	      System.exit (1);

	    // Turn on basic logging if the debug was selected
//	    basic.enabled = debug;
//	    basic.log ("target_args = %s%n", Arrays.toString (target_args));

	    // Start the target.  Pass the same options to the premain as
	    // were passed here.

	    PreDaikon chicory = new PreDaikon();
	    try {
			chicory.start_target (options.get_options_str(), target_args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static boolean check_args (Options options, String[] target_args) {

	    // Make sure arguments have legal values
	    if (nesting_depth < 0) {
	      options.print_usage ("nesting depth (%d) must not be negative",
	                           nesting_depth);
	      return (false);
	    }
	    if (target_args.length == 0) {
	      options.print_usage ("target program must be specified");
	      return (false);
	    }

	    return (true);

	  }
	
	//TODO: make it output daikon's command
	void start_target (String premain_args, String[] target_args) throws Exception {

	    // Default the trace file name to the <target-program-name>.dtrace.gz
	    if (dtrace_file == null) {
	      String target_class = target_args[0].replaceFirst (".*[/.]", "");
	      dtrace_file = new File (String.format ("%s.dtrace.gz", target_class));
	      premain_args += " --dtrace-file=" + dtrace_file;
	    }

	    // Get the current classpath
	    String cp = System.getProperty("java.class.path");
	    basic.log("classpath = '%s'\n", cp);
	    if (cp == null)
	      cp = ".";

	    // The the separator for items in the class path
	    String separator = System.getProperty("path.separator");
	    basic.log("separator = %s\n", separator);
	    if (separator == null) {
	      separator = ";"; //should work for windows at least...
	    } else {
	      if (!RegexUtil.isRegex(separator)) {
	        throw new Exception("Bad regexp " + separator + " for path.separator: " + RegexUtil.regexError(separator));
	      }
	    }

	    // Build the command line to execute the target with the javaagent
	    List<String> cmdlist = new ArrayList<String>();
	    cmdlist.add ("java");

	    cmdlist.add ("-cp");
	    cmdlist.add ("$CLASSPATH:" + cp);
//	    cmdlist.add ("-ea");
//	    cmdlist.add ("-Xmx" + heap_size);
	    cmdlist.add("daikon.Chicory \'--ppt-omit-pattern=^org\\.junit\\.|^junit\\.|^com\\.sun\\.proxy\\.\'");
	    cmdlist.add("--daikon");

	    for (String target_arg : target_args)
	      cmdlist.add (target_arg);
	    System.out.printf ("\nExecuting target program: %s\n",
	                         args_to_string(cmdlist));
	    
	    PrintWriter outFile = new PrintWriter(new BufferedWriter(new FileWriter("./exec_daikon", true)));
	    outFile.println(args_to_string(cmdlist));
	    outFile.flush();
	    outFile.close();
	    
//	    PrintWriter cmdFile = new PrintWriter("./exec_daikon");
//	    cmdFile.println(args_to_string(cmdlist));
//	    cmdFile.flush();
//	    cmdFile.close();
	    
	    System.out.println("Check the above argument ................................................");

//	    String[] cmdline = cmdlist.toArray(new String[cmdlist.size()]);

	  }
	
	/** convert a list of arguments into a command line string **/
	public String args_to_string(List<String> args){
		String str = "";
	    for (String arg : args)
	      str += arg + " ";
	    return (str.trim());
	}
}

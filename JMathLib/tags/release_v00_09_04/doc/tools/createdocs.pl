#!/usr/bin/perl

use XMLWriter;
use strict;
use warnings;

sub processFiles($);
sub writeDoc(%);
sub writeStart($$$$);
sub writeSection($$$$);
sub writeSeeAlso($$$$);
sub writeFinish($);
sub buildGroup($@);
sub processDirectory($);

print "extracting documentation information from sourcefiles \n";
print "argv $ARGV[0] \n";
print "argv $ARGV[1] \n";

#get the name of the output dir
my $docdir = $ARGV[0];
my $srcdir = $ARGV[1];
my %docs = ();

print "docdic $docdir\n";
print "docs %doc \n";

#processFiles($srcdir);

processDirectory($srcdir);

#die "asdfasdf";

writeDoc(%docs);

############ subroutines ##############

sub processDirectory($)
{
    my ($directory) = @_;
    my $file ="";
    my @filelist = ();

    # read all file from the given source directory
    opendir(DIR, $directory) or die "Cannot open $srcdir";

    while($file = readdir(DIR))
    {
        #chomp $file;
        #$file =~ s/^[CVS|src|_private]//g;

        #print "\n file: ".$file."\n";
        #my $ttt = <STDIN> ;
        
        
        #my @files = readdir(DIR);
    
        if (($file ne "") && ($file ne ".") && ($file ne "..") && ($file ne ".svn") && ($file ne "CVS"))
        {
            push(@filelist, $file);
            print "file ".$file."\n";
        }
    }
    closedir(DIR);

    
    while ($file = pop(@filelist))
    {
            my $fullfilename = $directory."/".$file ;
            
            #print "processing: ". $fullfilename . "\n";
            #my $ttt = <STDIN> ;
            
            if (-f $fullfilename)
            {
                print "processing file: ". $fullfilename . "\n";
                processFiles($fullfilename);
                print "processing finished file: ". $fullfilename . "\n";
            }
            elsif (-d $fullfilename)
            {
               print "processing dir: ". $fullfilename . "\n";
               processDirectory($fullfilename);
            }
            else
            {
                print "processing ???:". $fullfilename . "\n";    
            }
            
    } 
}

sub processFiles($)
{
    my ($filename) = @_;
    my $docmode      = "";
    my $oldfilename  = "";
    my %doc = ("DOC"        => "",
               "SYNTAX"     => "",
               "NOTES"      => "",
               "SEE"        => "",
               "EXAMPLES"   => "",               
               "GROUP"      => ""
              );
              
    #### start processing the files ####
    #print "processFile  $filename \n";
    
    # open jmathlib function
    my $filehandle;
    open(FILE, "<", $filename) or die "could not open file";           
               
    # read and process all lines of the given file
    while(my $line = readline(FILE))
    {
         #print "createdocs: line $line \n";
         
        if($filename ne $oldfilename)
        {
            if($oldfilename ne "")
            {
                if($doc{"DOC"} ne "")
                {
                    $doc{"NAME"} = $oldfilename;
                    my %newdoc = (  "DOC"        => $doc{"DOC"},
                                    "SYNTAX"     => $doc{"SYNTAX"},
                                    "NOTES"      => $doc{"NOTES"},
                                    "SEE"        => $doc{"SEE"},
                                    "EXAMPLES"   => $doc{"EXAMPLES"},               
                                    "GROUP"      => $doc{"GROUP"},
                                    "NAME"       => $doc{"NAME"}
                           );
                    my $group = $doc{"GROUP"};
                    my @list = ();
                    if($docs{$group})
                    {
                        @list = @{$docs{$group}};
                    }
                    push @list, \%newdoc;
                    $docs{$group} = \@list;
                }
                else
                {
                    #print "No docs for file $oldfilename\n";
                }                
            }

            %doc = ("GROUP"      => "",
                    "SYNTAX"     => "",
                    "DOC"        => "",
                    "EXAMPLES"   => "",               
                    "NOTES"      => "",
                    "SEE"        => ""
                   );
                   
            $oldfilename = $filename;
        }
    
        chomp $line;        
        #$line =~ s/<.*?>//g;
        #$line =~ s/</&lt;/g;
        #$line =~ s/<br>//g;
        #$line =~ s/<BR>//g;
        $line =~ s/&/&amp;/g;
        if($line =~ m"\*/")
        {
            $docmode = "";        
        }    
        elsif($line =~ m"\@(DOC|SYNTAX|NOTES|SEE|EXAMPLES|GROUP)")
        {
            $docmode = $1;
        }   
        elsif($docmode eq "GROUP")
        {
            $doc{"GROUP"} = "$line";
        }
        elsif($docmode eq "SOURCE")
        {
        }
        elsif($docmode ne "")
        {
            $doc{$docmode} .= "$line\n";
        }
        else
        {
            ;
        }
    }

    close(FILE);

    if($doc{"DOC"} ne "")
    {
        $doc{"NAME"} = $oldfilename;
        my %newdoc = ("DOC"        => $doc{"DOC"},
                "SYNTAX"     => $doc{"SYNTAX"},
                "NOTES"      => $doc{"NOTES"},
                "SEE"        => $doc{"SEE"},
                "EXAMPLES"   => $doc{"EXAMPLES"},               
                "GROUP"      => $doc{"GROUP"},
                "NAME"       => $doc{"NAME"}
               );
        my $group = $doc{"GROUP"};
        my @list = ();
        if($docs{$group})
        {
            @list = @{$docs{$group}};
        }
        push @list, \%newdoc;
        $docs{$group} = \@list;
    }
    else
    {
        #print "No docs for file >$oldfilename<\n";
    }    
}

sub writeDoc(%)
{
    my %docs = @_;
      
    # each key is a group (e.g. general, control, graphics, statistics)
    foreach my $key (sort keys %docs)
    {
        my $temp = $docs{$key};
        my @doclist = @$temp;
        buildGroup($key, @doclist);       
    }
}

sub buildGroup($@)
{
    my ($key, @doclist) = @_;

    my $dir = "$docdir/" . lc($key);
    mkdir $dir;
    print "\n build group for $key : \n ";

    foreach my $docitem (@doclist)
    {
        my $group = "";
        my $data = "";
        my $temp = "";
        my %doc = %$docitem;

        my $name = $doc{"NAME"};            
        $name = lc $name;
        $name =~ s".*/(.*)"$1";    
        $name =~ s/\.(java|m|int)//;
        my $type = $1;
        print " $name ";
    
        # name of the functions xml-file (e.g. 
        my $outputname = $dir . "/" . $name . ".xml";
        
        my $writer = new XMLWriter($outputname, ">");
        
        my $title = $writer->makeTag("title", $key) . "\n";

        # name of the function
        my $functitle = $writer->makeTag("title", $name);
        
        # index entry
        $temp .= "<indexterm><primary>" . $name . "</primary></indexterm>\n";
                
        $temp .= writeStart($writer, $name, $doc{"GROUP"}, $type);
       
        # write all sections
        $temp .= writeSection($writer, $name, "Syntax",      "<programlisting>".$doc{"SYNTAX"}."</programlisting>");
        $temp .= writeSection($writer, $name, "Description", $doc{"DOC"});
        $temp .= writeSection($writer, $name, "Notes",       $doc{"NOTES"});
        $temp .= writeSection($writer, $name, "Examples",    $doc{"EXAMPLES"});
        $temp .= writeSeeAlso($writer, $name, "See Also",    $doc{"SEE"});   
        

        #$data .= $writer->makeTag("sect1", $functitle . $temp) . "\n";

        $data .= '<sect1 id="function_' . $name . '">';
        $data .= $functitle . $temp;
        $data .= '</sect1>';

        $group = $data; #$writer->makeTag("section",  $title . $data);
    
        $writer->writeText($group);
    }
}

sub writeStart($$$$)
{
    my ($writer, $name, $group, $type) = @_;

    my $data = "";
    
    # create type
    my $t="";
    if($type eq "java")    
    {
        $t = "Type: " . " External";
    }
    elsif($type eq "m")
    {
        $t = "Type: " . " M-File";
    }
    elsif($type eq "int")
    {
        $t = "Type: " . " Internal";
    }
    else
    {
        $t = "Type: " . "unknown";
    }

    $data  = '<sect2 id="function_' . $name . '_type">';
    $data .= "<title>" . $t . "</title><simpara></simpara>";
    $data .= "</sect2>\n";

    
    $data .= '<sect2 id="function_' . $name . '_group">';
    $data .= "<title>" . "Group: " . $group . "</title><simpara></simpara>";
    $data .= "</sect2>\n";

    return $data;
}

sub writeSection($$$$)
{
    my ($writer, $name, $section, $text) = @_;
    
    my $data  = "";
    my $title = "";
    if($text ne "")
    {
        $title = $writer->makeTag("title", $section);
        
        $text =~ s/<.*?>(.*?)<.*?>/$1/g;
        $text  = $writer->makeTag("simpara", $text);
        
        $data .= '<sect2 id="function_' . $name . "_" . $section . '">' ."\n";
        #$data .= '<sect2>' ."\n";
        $data .= $title ."\n";
        $data .= $text ."\n";
        $data .= "</sect2>\n";
    }
    return $data;
}

# write the SeeAlso section. e.g.
#@SEE
#ylabel zlabel title
sub writeSeeAlso($$$$)
{
    my ($writer, $name, $section, $text) = @_;
    
    my $data  = "";
    my $title = "";
    my $links = "";
    
    if($text ne "")
    {
        my $title = "<title>" . $section . "</title>";
        
        $text =~ s/<.*?>(.*?)<.*?>/$1/g;
        
        my @functions = split(/\n| |,|;/ , $text  );
        foreach my $seealso (@functions)
        {
            if ($seealso ne "")
            {
                $links .= '<link linkend="function_' . $seealso . '">';
                #$links .= '<link>';
                $links .= $seealso;
                $links .= "</link>, \n";
            }
        }
        # remove trailing ", \n"
        chop $links;
        chop $links;
        chop $links;
        
        $data .= '<sect2 id="function_' . $name . '_SeeAlso">' . "\n";
        #$data .= '<sect2>' . "\n";
        $data .= $title ."\n";
        $data .= "<simpara>" . $links ."</simpara>\n";
        $data .= "</sect2>\n";
    }
    return $data;
}


#!/usr/bin/perl

use warnings;
use strict;
use XMLWriter;

sub processDirectory($);
sub readIntroductionFile($);

my $destdir = shift;
my $srcdir  = shift;
#$srcdir = $destdir . $srcdir;
print "using sourcedir $srcdir \n";

opendir(DIRECTORY, $srcdir) or die "Cannot open $srcdir";

my @files = grep !/^[\.|CVS]|src/, readdir(DIRECTORY);
closedir(DIRECTORY);

$destdir .= "/functions.xml";
my $writer = new XMLWriter($destdir);

foreach my $file (sort {lc($a) cmp lc($b)} @files)
{
    my $filename = $srcdir . "/" .$file;
    
    if (-d $filename)
    {
        print "processing subdirectory $filename \n";
        
        my $title    = $writer->makeTag("title", $file);
        my $filedata = processDirectory($filename);
        my $data     = "";
        
        #$data = $writer->makeTag("chapter", $title . $filedata);
        
        $data .= '<chapter id="group_' . $file . '">' ."\n";
        $data .= $title ."\n";
        
        # create description page
        my $description = readIntroductionFile($file);
        $data .= $description ."\n";
        
        $data .= $filedata ."\n";
        $data .= "</chapter>\n";
        
        $writer->writeText($data);
    }

}

sub processDirectory($)
{
    my $name = shift;
    opendir(DIRECTORY, $name) or die "Cannot open $srcdir";
    
    my @files = grep !/^[\.|CVS]|src/, readdir(DIRECTORY);
    closedir(DIRECTORY);

    my $data = "";            
    my $input;
    foreach my $file (sort {lc($a) cmp lc($b)} @files)
    {    
        print "processing $file \n";
        open ($input, "<", $name . "/" .$file);
        
        while(my $line = readline($input))
        {
            $data .= $line;
        }
    }
    
    return $data;
}

# check and read introduction files for toolboxes
sub readIntroductionFile($)
{
	my $name  = shift;
	my $file  = "toolbox_introduction_" . $name . ".xml";
	my $intro = "";
	
	print "file $file \n";

	# check if introduction file is available, if not return empty string
    open(FILE, "<", "../src/".$file) or  return ""; 
    
	print "processing introduction $file \n";
    
    while(my $line = readline(FILE))
    {
        $intro .= $line;
    }

    close(FILE); 
    
	return $intro;

}


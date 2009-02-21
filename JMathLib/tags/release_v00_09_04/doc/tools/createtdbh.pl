#!/usr/bin/env perl
use XMLWriter;
use strict;
use warnings;


my $buglist     = "../../../Changes/bugs.txt";
my $versionlist = "../versions.txt";


############# buglist #############
print "Creating buglist \n";


my $inputfile;

open ($inputfile, "<", $buglist);
my $writer = new XMLWriter("../bugs.xml");

my $open = "";
my $closed = "";
my $part = "open";
while(my $line = readline($inputfile))
{
    if ($line =~ m/Resolved/i)
    {
        $part = "closed";
    }
    elsif ($line !~ m/Open/i)
    {
        chomp $line;
        if ($part eq "open")
        {
            $open .= $writer->makeTag("simpara", $line) . "\n";
        }
        else
        {
            $closed .= $writer->makeTag("simpara", $line) . "\n";
        }
    }
}
my $title = $writer->makeTag("title", "Open") . "\n";
my $data = $writer->makeTag('sect1', $title . $open) . "\n";
$writer->writeText($data);

$title = $writer->makeTag("title", "Closed") . "\n";
$data = $writer->makeTag('sect1', $title . $open) . "\n";
$writer->writeText($data);

############# to do list #############
print "Creating ToDo list \n";

my $todolist = "../../../Changes/ToDo.txt";

open $inputfile, "<", $todolist;
$writer = new XMLWriter("../todo.xml");

$open = "";
$closed = "";
$part = "open";
while(my $line = readline($inputfile))
{
    if ($line =~ m/Completed/i)
    {
        $part = "closed";
    }
    elsif ($line !~ m/Open/i)
    {
        chomp $line;
        if ($part eq "open")
        {
            $open .= $writer->makeTag("simpara", $line) . "\n";
        }
        else
        {
            $closed .= $writer->makeTag("simpara", $line) . "\n";
        }
    }
}
$title = $writer->makeTag("title", "Open") . "\n";
$data = $writer->makeTag('sect1', $title . $open) . "\n";
$writer->writeText($data);

$title = $writer->makeTag("title", "Closed") . "\n";
$data = $writer->makeTag('sect1', $title . $open) . "\n";
$writer->writeText($data);


############## history list #############
print "Creating history list \n";

open ($inputfile, "<", $versionlist);

my %versions=();
while(my $line = readline($inputfile))
{
    chomp $line;
    if($line ne "")
    {
        my @list = split "=", $line;
    
        print $list[1]. " = " . $list[0] . "\n";
        $versions{$list[1]} = $list[0];
    }
}


my %dates=();

foreach my $filename ("Alex", "Mark", "Stefan")
{
    # process changes of all users
    my $date = "";
    my $changes="../../../Changes/$filename.txt";
    
    print "processing changes of $filename \n";
    open $inputfile, "<", $changes || die "cannot open file for $filename";
    
    while(my $line = readline($inputfile))
    {
        chomp $line;
        if($line ne "")
        {
            if($line =~ s"(\d\d\d\d/\d\d/\d\d)"")   #check the line for a date
            {
                $date = $1;
                print "processing change on day $date \n";
            }
            
            #parse any symbols in the line
            $line =~ s/\+ /added /;
            $line =~ s/- /removed /;
            $line =~ s/!|#|\* /updated /;
            
            if($date ne "")
            {
                my @list = ();
                if($dates{$date})
                {
                    @list = @{$dates{$date}};
                }
                #print "added $line \n";
                push @list, $line;
                $dates{$date} = \@list;
            }
        }
    }
}
    
print "finished parsing individual change files \n";
    
#my $waiting = <STDIN>;
          
$writer = new XMLWriter("../history.xml");

$data = "";
my $olddate = "0.4.5";
foreach my $key (sort {$b cmp $a} keys %dates)
{
    # start with newest changes
    my $temp = $dates{$key};    
    my @list = @$temp;
    #print "processing key $key \n";
    
    foreach my $version (sort keys %versions)
    {
        my $releasedate = $versions{$version};
        #print "changes before $releasedate \n";
        
        if($key lt $releasedate)
        {            
            $data .= $writer->makeTag("simpara", "$key") . "\n";                
            foreach my $item (@list)
            {
                $item =~ s/&/&amp;/g;
                $item =~ s"\\"\\ ";
                $data .= $writer->makeTag("simpara", "$item") . "\n";                
            }

            if($version ne $olddate)
            {
                my $title = $writer->makeTag("title", "Version $olddate\n") . "\n";                
                $writer->writeText($writer->makeTag("sect1", $title . $data));
                $data = "";
                $olddate = $version;                
            }
            $key = "xxxxxx";
        }
    }
}

package HTMLWriter;

use strict;
use warnings;

1;

sub new($)
{
    my $class = shift;
    my $name = shift;
    
    open my $handle, ">", $name or die "can't open $name";
    #my $obj= {"handle" => $handle};
    bless $handle, $class;    
    return $handle;
}

sub html
{
    my $handle = shift;
    my $data  = "";

    print $handle "<HTML>\n";  
    #print $handle $data;    
}

sub endhtml
{
    my $handle = shift;
    print $handle  "</HTML>\n";  
}

sub header
{
    my $handle = shift;
    my $title = shift;
    
    my $data = "";
    $data .= "<HEADER>\n";  
    $data .= "<TITLE>$title</TITLE>\n";  
    $data .= "</HEADER>";  
    
    print $handle $data;    
}

sub body
{
    my $handle = shift;

    my $data = "<BODY>\n";  

    print $handle $data;    
}

sub endbody
{
    my $handle = shift;
    print $handle "</BODY>\n";
}

sub writeText($$)
{
    my $handle = shift;
    my $text = shift;
    
    print $handle "$text\n";
}

sub center($)
{
    my $handle = shift;
    my $text = shift;
    
    my $data = "<CENTER>$text</CENTER>";
    
    return $data;
}

sub heading
{
    my $handle = shift;
    my ($size, $text) = @_;
    
    my $data = "<H$size>$text</H$size>\n";
    
    return $data;    
}

sub bold
{
    my $handle = shift;
    my $text = shift;
    return "<B>$text</B>";
}

sub hr
{
    my $handle = shift;
    print $handle "<HR>";    
}

sub paragraph
{
    my $handle = shift;
    my ($text) = @_;
    
    my $data = "<P>$text</P>\n";
    
    return $data;    
}

sub link
{
    my $handle = shift;
    my ($ref, $text) = @_;
    
    my $data = "<A HREF=\"$ref\">$text</A>";
        
    return $data;    
}

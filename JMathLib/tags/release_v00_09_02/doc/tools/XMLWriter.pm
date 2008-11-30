package XMLWriter;

use strict;
use warnings;

1;

sub new($$)
{
    my $class = shift;
    my $name = shift;
    my $mode = shift;
    $mode = $mode || ">";
    
    open my $handle, $mode, $name or die "can't open $name";
    #my $obj= {"handle" => $handle};
    bless $handle, $class;    
    return $handle;
}


sub writeText($$)
{
    my $handle = shift;
    my $text = shift;
    
    print $handle "$text\n";
}

sub makeTag($$$@)
{
    my $handle = shift;
    my $name   = shift;
    my $text   = shift;

    my $start = $name;
    for my $att (@_)
    {
        $start = $start . " " . $att;
    }
    
    return "<" . $start . ">" . $text . "</" . $name . ">";
}

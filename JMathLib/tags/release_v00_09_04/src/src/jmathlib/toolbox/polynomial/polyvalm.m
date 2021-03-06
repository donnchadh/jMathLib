## Copyright (C) 1994, 1995, 1996, 1997, 1998, 1999, 2000, 2002, 2004,
##               2005, 2006, 2007 John W. Eaton
##
## This file is part of Octave.
##
## Octave is free software; you can redistribute it and/or modify it
## under the terms of the GNU General Public License as published by
## the Free Software Foundation; either version 3 of the License, or (at
## your option) any later version.
##
## Octave is distributed in the hope that it will be useful, but
## WITHOUT ANY WARRANTY; without even the implied warranty of
## MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
## General Public License for more details.
##
## You should have received a copy of the GNU General Public License
## along with Octave; see the file COPYING.  If not, see
## <http://www.gnu.org/licenses/>.

## -*- texinfo -*-
## @deftypefn {Function File} {} polyvalm (@var{c}, @var{x})
## Evaluate a polynomial in the matrix sense.
##
## @code{polyvalm (@var{c}, @var{x})} will evaluate the polynomial in the
## matrix sense, i.e. matrix multiplication is used instead of element by
## element multiplication as is used in polyval.
##
## The argument @var{x} must be a square matrix.
## @seealso{polyval, poly, roots, conv, deconv, residue, filter,
## polyderiv, polyinteg}
## @end deftypefn

## Author: Tony Richardson <arichard@stark.cc.oh.us>
## Created: June 1994
## Adapted-By: jwe

function y = polyvalm (c, x)

  if (nargin != 2)
    print_usage ();
  endif

  if (! (isvector (c) || isempty (c)))
    error ("polyvalm: first argument must be a vector");
  endif

  if (! issquare (x))
    error ("polyvalm: second argument must be a square matrix");
  endif

  if (isempty (c))
    y = [];
    return;
  endif

  [v, d] = eig (x);

  if (issymmetric (x))
    y = v * diag (polyval (c, diag (d))) * v';
  else
    y = v * (diag (polyval (c, diag (d))) / v);
  endif

endfunction

//%!assert(isempty (polyvalm ([], [1, 2; 3, 4])));

//%!error polyvalm ([1, 1, 1], [1, 2; 3, 4; 5, 6]);


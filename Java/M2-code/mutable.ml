;; open Assert

(* Commands are useful because they *do* things. *)

let x = 31 + 89
;; print_endline (string_of_int x)


(* Can embed commands inside functions! *)

let f (x : int) : unit = 
  print_string "f called with ";
  print_endline (string_of_int x);
  ignore (x + x);      (* must mark values that are not used *)
  print_string "end"   (* no ; at end! *)
  
;; let x = f 120
;; x = ()


(* What is the result type of print_string? *)

(* print_string : string -> unit  *)

let g (x:int) : unit = 
  print_string "g called with ";
  print_endline (string_of_int x)
  

(* a testing function, what is its type? *)
let test () : bool = 4 = 5

let h = test ()


(* Can call test by providing () value *)


(* run_test : string -> (unit -> bool) -> unit *)
;; run_test "4 is 5" test




(* Functions can take unit as an argument. *)

let f (x : unit) : int = 3


(* unit: Just like any other value. *)

let g (x:unit) : int = 
  begin match x with
  | () -> 3
  end


(* if: then without an else *)

;; if false then print_string "not a chance" (* else () *)




(* ------- (Immutable) records ---------  *)

(* a type for representing colors *)
type rgb = {r:int; g:int; b:int;}

(* some example rgb values *)
let red   : rgb = {r=255; g=0;   b=0;}
let blue  : rgb = {r=0;   g=0;   b=255;}
let green : rgb = {r=0;   g=255; b=0;}
let black : rgb = {r=0;   g=0;   b=0;}
let white : rgb = {r=255; g=255; b=255;}

(* using 'dot' notation to project out components *)
(* calculate the average of two colors *)
let average_rgb (c1:rgb) (c2:rgb) : rgb = 
  {r = (c1.r + c2.r) / 2;
   g = (c1.g + c2.g) / 2;
   b = (c1.b + c2.b) / 2;}











(*  MUTABLE RECORDS *)

type point = {mutable x:int; mutable y:int}

let string_of_point (p : point) : string = 
  "<" ^ (string_of_int p.x) ^ "," ^ (string_of_int p.y) ^ ">"

let p0 = {x=0; y=0}
(* ;; print_endline (string_of_point p0) *)
(* set the x coord of p0 to 17 *)
;; p0.x <- 17



(* a command to shift a point by dx,dy *)
let shift (p:point) (dx:int) (dy:int) : unit =
  p.x <- p.x + dx;
  p.y <- p.y + dy;
  print_endline ("p = " ^ string_of_point p)

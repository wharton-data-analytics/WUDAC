;; open Gctx
;; open SimpleWidget

(* This paint function draws a fractal tree -- Google "L-systems" for  *)
(* more explanation / inspiration *)
let paint_tree (g:Gctx.gctx) : unit =
    let rec fractal ((x,y):int*int) (angle:int) (len:int) =
        if len <= 1 then () 
        else
            let lf = float_of_int len in
            let af = ((float_of_int angle) *. 3.14159) /. 180.0 in
            let nx = x + (int_of_float (lf *. (cos af))) in
            let ny = y + (int_of_float (lf *. (sin af))) in
            begin 
                Gctx.draw_line g (x,y) (nx, ny);
                fractal (nx, ny) (angle + 20) (len - 2);
                fractal (nx, ny) (angle - 10) (len - 1)
            end
    in 
      fractal (75,100) 270 15

let c = canvas (200, 200) 
  (fun g -> paint_tree (with_color g green))

let run (w:widget) : unit =
  Gctx.open_graphics ();             (* open graphics window *)
  let top = Gctx.top_level in
  w.repaint top;                       (* repaint the widget once *)
  Graphics.synchronize ();           (* force window update *)
  ignore (Graphics.read_key ())      (* wait for a keypress *)
  
let l1 = label "hello"
let l2 = label "world"
let w = hpair (hpair (border l1) (space (10,10))	)
              (border l2)
	
;; run (hpair (border c) (border w))

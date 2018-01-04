(* An interface for simple GUI widgets *)
type widget = { 
   repaint : Gctx.gctx -> unit; 
   size    : unit -> (int * int) 
} 

(* A simple widget that puts some text on the screen *)
let label (s:string) : widget =
{ 
  repaint = (fun (g:Gctx.gctx) -> Gctx.draw_string g (0,0) s);
  size = (fun () -> Gctx.text_size s) 
}

let space ((x, y):int*int) : widget =
{
  repaint = (fun (g:Gctx.gctx) -> ());
  size = (fun () -> (x,y))
}

let canvas ((w,h):int*int) (repaint: Gctx.gctx -> unit) : widget =
{
  repaint = repaint;
  size = (fun () -> (w,h))
} 

let border (w:widget):widget =
{ 
repaint = (fun (g:Gctx.gctx) ->
  let (width,height) = w.size () in
  let x = width + 3 in    
  let y = height + 3 in 
  Gctx.draw_line g (0,0) (x,0);
  Gctx.draw_line g (0,0) (0,y);
  Gctx.draw_line g (x,0) (x,y);
  Gctx.draw_line g (0,y) (x,y);
  let g = Gctx.translate g (2,2) in
  w.repaint g);

size = (fun () ->
  let (width,height) = w.size () in
  (width+4, height+4))
}

let hpair (w1:widget) (w2:widget) : widget =
{
  repaint = (fun (g:Gctx.gctx) ->
    let (x1,_) = w1.size () in begin
      w1.repaint g;
      w2.repaint (Gctx.translate g (x1,0))  
      (* Note translation of the Gctx *)
    end);

size = (fun () ->
  let (x1,y1) = w1.size () in
  let (x2,y2) = w2.size () in
    (x1 + x2, max y1 y2))
}


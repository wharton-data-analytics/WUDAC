;; open Gctx
;; open Widget

type bulb_state = { mutable on : bool }

let mk_bulb () : widget * widget =
  let bulb = { on = false } in
  let toggle () = bulb.on <- not (bulb.on) in
  let paint_bulb (g: Gctx.gctx) =
    let g_new = Gctx.with_color g (if bulb.on then yellow else black) in
    Gctx.fill_rect g_new (0,99) (99,99) in
  let (c, _) = canvas (100,100) paint_bulb in
  let (button1, lc1, nc1) = button "ON " in
  let button_action () =
    toggle ();
    if bulb.on then lc1.set_label "OFF" else lc1.set_label "ON "
  in
  nc1.add_event_listener (mouseclick_listener button_action);
  (c, button1)
  
let (b1,button1) = mk_bulb ()
let (b2,button2) = mk_bulb ()

let (qb, _, nc) = button "QUIT"

;; nc.add_event_listener (mouseclick_listener (fun () -> exit 0))

let w = hpair b2 (hpair b1  (hpair (border button2) (hpair (border button1) (border qb))))

;; Eventloop.run w

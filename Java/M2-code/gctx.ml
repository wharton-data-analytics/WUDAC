(** The "Graphics Context" component of the GUI library. *)

(** A graphics context represents a portion of the window to which
    widgets will be drawn.

    The drawing primitives in this module are all relative to the
    graphics context. This means that when a widget needs to draw on the
    screen, it need not know its absolute position. The graphics context
    is responsible for translating the relative positions passed into the
    drawing routines into absolute positions on the screen.

    The graphics context also includes other information for basic
    drawing (such as the current pen color.)

    Note that this module defines a persistent (immutable) data
    structure. The operations here use a given graphics context to create
    a new one with the specified characteristics. They do not modify
    their arguments. *)


(****************)
(** {1 Colors } *)
(****************)

(** A type for colors *)
type color = {r:int; g:int; b:int}

let black   : color = {r=0;  g=0;  b=0}
let white   : color = {r=255;g=255;b=255}
let red     : color = {r=255;g=0;  b=0}
let green   : color = {r=0;  g=255;b=0}
let blue    : color = {r=0;  g=0;  b=255}
let yellow  : color = {r=255;g=255;b=0}
let cyan    : color = {r=0;  g=255;b=255}
let magenta : color = {r=255;g=0;  b=255}


(*******************************)
(** Basic Gctx operations      *)
(*******************************)

(** The main type of graphics contexts. Note that none of the components
    are mutable. *)
(* TODO: You will need to modify this type definition for Task  5. *)
type gctx = {
  x: int;         (** offset from (0,0) in local coordinates *)
  y: int;
  color: color;   (** the current pen color *)
}


(* has the graphics window been opened already? *)
let graphics_opened = { contents = false; }
(** Open the graphics window, but only do it once *)
let open_graphics () =
    if not graphics_opened.contents then
      begin
        graphics_opened.contents <- true;
        Graphics.open_graph "";             (* open the window        *)
        Graphics.auto_synchronize false;    (* don't draw immediately *)
        Graphics.clear_graph ();
        Graphics.synchronize ()
      end

(** The top-level graphics context. *)
let top_level : gctx =
  { x = 0;
    y = 0;
    color = black;
     }

(** Widgets can translate (move) a graphics context to obtain an
appropriate graphics context for their children. *)

(** Shift the gctx by (dx,dy) *)
let translate (g: gctx) ((dx, dy): int * int) : gctx =
  { g with x = g.x + dx; y = g.y + dy }

(** Produce a new Gctx.t with a different pen color *)
let with_color (g: gctx) (c: color) : gctx =
  { g with color = c }


(** Set the OCaml graphics library's internal state so that it
agrees with the Gctx settings.

Initially, this just sets the current pen color.
*)
(* TODO: You will need to modify this definition for Task 5. *)
let set_graphics_state (gc: gctx) : unit =
  let {r;g;b} = gc.color in
  Graphics.set_color (Graphics.rgb r g b)
  

(************************************)
(*    Coordinate Transformations    *)
(************************************)

(* The default width and height of the graphics window that OCaml opens.   *)

let graphics_size_x () =
  if graphics_opened.contents then Graphics.size_x () else 600
let graphics_size_y () =
  if graphics_opened.contents then Graphics.size_y () else 450

(* A main purpose of the graphics context is to provide mapping between    *)
(* widget-local coordinates and the ocaml coordinates of the graphics      *)
(* library. Part of that translation comes from the offset stored in the   *)
(* graphics context itself. The translation needs to know where the widget *)
(* is on the screen. The other part of the translation is the y axis flip. *)
(* The OCaml library puts (0,0) at the bottom left corner of the window.   *)
(* We'd like our GUI library to put (0,0) at the top left corner and       *)
(* increase the y-coordinate as we go *down* the screen.                   *)

(** A widget-relative position *)
type position = int * int

(* ALWAYS call these functions before passing widget-local points to the   *)
(* OCaml native Graphics module or vice-versa.                             *)

(** Convert widget-local coordinates (x,y) to OCaml graphics
    coordinates, relative to the graphics context.                         *)
let ocaml_coords (g: gctx) ((x, y): position) : (int * int) =
  (g.x + x, graphics_size_y () - 1 - (g.y + y))

(** Convert OCaml Graphics coordinates (x,y) to widget-local graphics
    coordinates, relative to the graphics context                          *)
let local_coords (g: gctx) ((x, y): int * int) : position =
  (x - g.x, (graphics_size_y () - 1 - y) - g.y)



(*****************)
(** {1 Drawing } *)
(*****************)

(** A width and height, paired together. *)
type dimension = int * int

(* Each of these functions takes inputs in widget-local coordinates,       *)
(* converts them to OCaml coordinates, and then draws the appropriate      *)
(* shape.                                                                  *)

(** Draw a line between the two specified positions *)
let draw_line (g: gctx) (p1: position) (p2: position) : unit =
  set_graphics_state g;
  let (x1, y1) = ocaml_coords g p1 in
  let (x2, y2) = ocaml_coords g p2 in
  Graphics.moveto x1 y1;
  Graphics.lineto x2 y2

(** Display text at the given position *)
let draw_string (g: gctx) (p: position) (s: string) : unit =
  set_graphics_state g;
  let (_, height) = Graphics.text_size s in
  let (x, y) = ocaml_coords g p in
  (* subtract: working with Ocaml coordinates *)
  Graphics.moveto x (y - height);
  Graphics.draw_string s

(** Display a rectangle with lower-left corner at position
    with the specified dimension. *)
(* TODO: you will need to make this function actually draw a
   rectangle for Task 0.                                                  *)
let draw_rect (g: gctx) (p1: position) ((w, h): dimension) : unit =
  failwith "Gctx.draw_rect: unimplemented"

(** Display a filled rectangle with lower-left corner at positions
   with the specified dimension. *)
let fill_rect (g: gctx) (p1: position) ((w, h): dimension) : unit =
  set_graphics_state g;
  let (x, y) = ocaml_coords g p1 in
  Graphics.fill_rect x y w h

(** Draw an ellipse at the given position with the given radii *)
(* TODO: you will need to make this function actually draw an
   ellipse for Task 0.  *)
let draw_ellipse (g: gctx) (p: position) (rx: int) (ry: int) : unit =
  failwith "Gctx.draw_ellipse: unimplemented"


(** Calculates the size of a text when rendered. *)
let text_size (text: string) : dimension =
  (* Make sure that the graphics window has been opened. All of the other functions 
     require a graphics context, which is difficult to get without opening the graphics
     window first. But this one does not, so is a source of subtle bugs. *)
  open_graphics ();
  Graphics.text_size text

(* TODO: You will need to add several "wrapped" versions of ocaml graphics *)
(* functions here for Tasks 2, 4, and possibly 5 and 6 *)


(************************)
(** {1 Event Handling } *)
(************************)

(* This part of the module adapts OCaml's native event handling to     *)
(* something that more closely resembles that found in Java.           *)

(** Types of events that could occur *)
type event_type =
  | KeyPress of char    (* Key pressed on the keyboard.      *)
  | MouseDown           (* Mouse button pressed.             *)
  | MouseUp             (* Mouse button released.            *)
  | MouseMove           (* Mouse moved with the button up.   *)
  | MouseDrag           (* Mouse moved with the button down. *)


let string_of_event_type (et : event_type) : string =
  begin match et with
    | KeyPress k -> "KeyPress at " ^ (String.make 1 k)
    | MouseDrag  -> "MouseDrag"
    | MouseMove  -> "MouseMove"
    | MouseUp    -> "MouseUp"
    | MouseDown  -> "MouseDown"
  end


(** An event records its type and the widget-local position of
    the mouse when the event occurred. *)
type event = event_type * position

(** Accessor for the type of an event. *)
let event_type (e: event) : event_type =
  fst e


(** Accessor for the widget local position of an event. *)
let event_pos (e: event) (g : gctx) : position =
  local_coords g (snd e)

(** Convert an event to a string *)
let string_of_event ((ty, (x, y)): event) : string =
  (string_of_event_type ty) ^ " at " ^ (string_of_int x) ^ ","
  ^ (string_of_int y)

(** Make a dummy event for testing. *)
let make_test_event (et : event_type) ((x, y) : position) =
  (et, (x, graphics_size_y () - y))


let string_of_status (s: Graphics.status) : string =
  "(" ^ (string_of_int s.Graphics.mouse_x) ^ ","
  ^ (string_of_int s.Graphics.mouse_y) ^ ") b:"
  ^ (string_of_bool s.Graphics.button)
  ^ (if s.Graphics.keypressed
    then "k:" ^ (String.make 1 s.Graphics.key)
    else "")

(* The last function is used by the eventloop to get the next   *)
(* event from the graphics library. The events reported by the  *)
(* library are not as informative as we would like, so this     *)
(* function also processes them into a more convenient form.    *)
(* You don't need to understand how this processing works, but  *)
(* you do need to understand the various forms of events that   *)
(* this function generates.                                     *)

(** Wait for a mouse or key event. *)
let wait_for_event : unit -> event =

  let initial_status : Graphics.status =
    { Graphics.mouse_x =0;
      Graphics.mouse_y =0;
      Graphics.key = ' ';
      Graphics.button = false;
      Graphics.keypressed = false } in

  let last_status = ref initial_status in

  let compute_rich_event
      (s0 : Graphics.status)
      (s1 : Graphics.status) : event_type option =

    if s1.Graphics.keypressed then Some (KeyPress s1.Graphics.key)
    else if s0.Graphics.button <> s1.Graphics.button then
      (* change in button state *)
      begin
        if s1.Graphics.button then Some MouseDown else Some MouseUp
      end
    else if (s0.Graphics.mouse_x <> s1.Graphics.mouse_x ) ||
            (s0.Graphics.mouse_y <> s1.Graphics.mouse_y ) then
      (* mouse motion *)
      begin
        if s1.Graphics.button then Some MouseDrag else Some MouseMove
      end
    else None in

  fun () ->
      let rec loop () =
        let status = Graphics.wait_next_event
            [Graphics.Mouse_motion; Graphics.Button_down;
             Graphics.Button_up; Graphics.Key_pressed] in
        let event_type = compute_rich_event !last_status status in
        begin match event_type with
          | None -> (last_status := status; loop ())
          | Some ty ->
              (last_status := status;
                let event = (ty, (status.Graphics.mouse_x,
                                 status.Graphics.mouse_y)) in
                event)
        end in
      loop ()

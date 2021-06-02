; Este programa recibe por la línea de comandos un texto y lo escribe en un archivo llamado TEXTO.TXT 
; Repite el texto 100 veces


datos segment

  Handle dw ?
  Buffy db 128 dup (?)
  Nombre db "TEXTO.TXT",0
  N  dw  ?   ; tamaño del texto a almacenar    

  msgerror db "Error al procesar$"
  K db 100    ; cantidad de veces que vamos a copiar la línea de comandos en el archivo 

datos endS

pila segment stack 'stack'
   dw 256 dup(?)
pila endS



codigo segment
       Assume CS:codigo,DS:datos,SS:pila




printAX proc
; imprime a la salida estándar un número que supone estar en el AX
; supone que es un número positivo y natural en 16 bits.
; lo imprime en decimal.  
    
    push AX
    push BX
    push CX
    push DX

    xor cx, cx
    mov bx, 10
ciclo1PAX: xor dx, dx
    div bx
    push dx
    inc cx
    cmp ax, 0
    jne ciclo1PAX
    mov ah, 02h
ciclo2PAX: pop DX
    add dl, 30h
    int 21h
    loop ciclo2PAX

    pop DX
    pop CX
    pop BX
    pop AX
    ret
printAX endP


main: mov ax, pila
      mov ss, ax

      push ds
      pop es

      mov ax, datos
      mov ds, ax


      mov si, 80h
      xor ch, ch
      mov cl, byte ptr es:[si]

      cmp cl, 0
      je salir

      xor di,di
      inc si
      inc si
      dec cx 
      mov N, cx
cicLC: mov al, byte ptr es:[si]
       mov byte ptr buffy[di], al
       inc si
       inc di
       loop cicLC

       mov byte ptr buffy[di], 13
       inc di
       mov byte ptr buffy[di], 10
       add N, 2
       
      mov ah, 3Ch  ; crear el archivo  
      lea dx, nombre
      xor cx, cx
      int 21h
     
      jc error
    
      mov handle, ax
        
escribir:
      cmp K, 0
      je cerrar
      ; Aca escribimos el buffer con el texto en el archivo

      mov ah, 40h
      mov cx, N
      lea dx, Buffy
      mov bx, handle
      int 21h
    
      dec K
      jmp escribir

      jc error



cerrar:      
      mov ah, 3Eh ; cerrar el archivo
      mov bx, handle
      int 21h 

      jmp salir
error: lea dx, msgerror
       mov ah, 09h
       int 21h

salir:

      mov ax, 4C00h

      int 21h 

codigo ends

end main
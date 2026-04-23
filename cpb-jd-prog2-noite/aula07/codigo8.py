import pygame
from pygame import time

pygame.init()


tela = pygame.display.set_mode((800, 600), 0, 32)

#   Red      Green    Blue   Opacidade
# 00000000 00000000 00000000 00000000
# 0  - 255 0  - 255 0  - 255 0  - 255

jogando = True
clock = time.Clock()
pos = (0, 0)
estado = "desenhando"
cor = (255, 255, 0)

while jogando:    # Loop do Jogo
    # Calcular regras
    

    # Desenhar na Tela
    if estado == "limpando":
        tela.fill( (0, 0, 0) ) # Pinta a surface toda de preto
    elif estado == "cor_amarelo":
        cor = (255, 255, 0)
    elif estado == "cor_azul":
        cor = (0, 0, 255)
    elif estado == "cor_vermelho":
        cor = (255, 0, 0)
    estado = "desenhando"
    pygame.draw.circle(tela, cor, pos, 30, 0)
    pygame.display.update()
    clock.tick( 30 ) # quantidade de FPS

    # Captura de Eventos
    lista_eventos = pygame.event.get()
    # print(lista_eventos)
    for e in lista_eventos:
        if e.type == pygame.QUIT:
           jogando = False
        elif e.type == pygame.MOUSEMOTION:
            # print(f"Pos: {e.pos}")
            pos = e.pos
        elif e.type == pygame.KEYDOWN:
            if e.key == pygame.K_c:
                estado = "limpando"
            elif e.key == pygame.K_a:
                estado = "cor_amarelo"
            elif e.key == pygame.K_b:
                estado = "cor_azul"
            elif e.key == pygame.K_r:
                estado = "cor_vermelho"

        
           

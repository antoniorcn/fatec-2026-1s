import pygame
from pygame import time

pygame.init()


tela = pygame.display.set_mode((800, 600), 0, 32)

jogando = True
clock = time.Clock()
cor = (255, 255, 0)
vel_jogador = 6
x = 0
vel_x = 0
cor_inimigo = (0, 0, 255)

while jogando:    # Loop do Jogo
    # Calcular regras
    x = x + vel_x
    if x > 800:
        vel_x = -vel_jogador
    if x < 0:
        vel_x = vel_jogador
    soma_raios = 30 + 30
    distancia = ((x - 600) ** 2 + (300 - 300) ** 2) ** (1/2)
    if distancia < soma_raios:
        cor_inimigo = (255, 0, 0)
    else:
        cor_inimigo = (0, 0, 255)


    # Desenhar na Tela
    tela.fill((0, 0, 0))
    pygame.draw.circle(tela, cor, (x, 300), 30, 0)
    pygame.draw.circle(tela, cor_inimigo, (600, 300), 30, 0)
    pygame.display.update()
    clock.tick( 30 ) # quantidade de FPS

    # Captura de Eventos
    lista_eventos = pygame.event.get()
    for e in lista_eventos:
        if e.type == pygame.QUIT:
           jogando = False
        elif e.type == pygame.KEYDOWN:
            if e.key == pygame.K_d:
                vel_x = vel_jogador
            elif e.key == pygame.K_a:
                vel_x = -vel_jogador
        elif e.type == pygame.KEYUP:
            if e.key == pygame.K_d and vel_x > 0:
                vel_x = 0
            elif e.key == pygame.K_a and vel_x < 0:
                vel_x = 0

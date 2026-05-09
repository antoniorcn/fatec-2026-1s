import pygame

pygame.init()

SCREEN_SIZE = (800, 600)

PRETO = (0, 0, 0)
VERMELHO = (255, 0, 0)

screen = pygame.display.set_mode(SCREEN_SIZE, 0, 32)

jogando = True
x = 0
y = 300
vel_x = 0
vel_y = 0

while jogando:
    # Calcular as regras
    x = x + vel_x
    y = y + vel_y

    # Desenhar a tela
    screen.fill( PRETO )
    pygame.draw.circle( screen, VERMELHO, (x, y), 40.0, 2 )
    pygame.display.update()

    # Capturar os eventos
    for e in pygame.event.get():
        if e.type == pygame.QUIT:
            jogando = False
        elif e.type == pygame.KEYDOWN:
            if e.key == pygame.K_RIGHT:
                vel_x = 1
            elif e.key == pygame.K_LEFT:
                vel_x = -1
            elif e.key == pygame.K_UP:
                vel_y = -1
            elif e.key == pygame.K_DOWN:
                vel_y = 1
        elif e.type == pygame.KEYUP:
            if e.key == pygame.K_RIGHT:
                vel_x = 0
            elif e.key == pygame.K_LEFT:
                vel_x = 0
            elif e.key == pygame.K_UP:
                vel_y = 0
            elif e.key == pygame.K_DOWN:
                vel_y = 0                
            

print("Fim do jogo")
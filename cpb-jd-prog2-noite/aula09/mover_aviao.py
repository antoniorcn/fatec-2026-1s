import pygame

pygame.init()

SCREEN_SIZE = (800, 600)

PRETO = (0, 0, 0)
VERMELHO = (255, 0, 0)

screen = pygame.display.set_mode(SCREEN_SIZE, 0, 32)
aviao = pygame.image.load("./images/aviao.png")
tiro = pygame.image.load("./images/tiro.png")

jogando = True
x = 0
y = 300
vel_x = 0
vel_y = 0

tiro_x = 0
tiro_y = 0
vel_tiro = 0

mostrar_tiro = False

while jogando:
    # Calcular as regras
    x = x + vel_x
    y = y + vel_y
    tiro_y = tiro_y + vel_tiro

    # Desenhar a tela
    screen.fill( PRETO )
    screen.blit( aviao, (x, y) )

    if mostrar_tiro:
        screen.blit( tiro, (tiro_x, tiro_y) )

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
            elif e.key == pygame.K_f:
                mostrar_tiro = True
                tiro_x = x
                tiro_y = y
                vel_tiro = -1
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
# Atividade, transformar a classe Inimigo em um Sprite também 


import pygame

pygame.init()

class Player(pygame.sprite.Sprite):

    # Constructor. Pass in the color of the block,
    # and its x and y position
    def __init__(self, x, y, img ):
       # Call the parent class (Sprite) constructor
        pygame.sprite.Sprite.__init__(self)
        self.vel_x = 0
        self.vel_y = 0
        self.image = img
        self.rect = pygame.Rect( (x, y), (174, 132) )
       
    def update(self):
        self.rect.x = self.rect.x + self.vel_x
        self.rect.y = self.rect.y + self.vel_y

    def esquerda(self):
        self.vel_x = -1

    def direita(self):
        self.vel_x = 1

    def parar_horizontal(self):
        self.vel_x = 0


class Tiro():
    dano = 0
    x = 0
    y = 0
    vel = 0
    imagem = None

    def mostrar(self, tela):
        tela.blit(self.imagem, (self.x, self.y))


class Inimigo():
    x = 0
    y = 0
    vel_x = 0
    vel_y = 0
    imagem = None
    hp  = 30

    def mostrar(self, tela):
        tela.blit(self.imagem, (self.x, self.y))


SCREEN_SIZE = (800, 600)

PRETO = (0, 0, 0)
VERMELHO = (255, 0, 0)

screen = pygame.display.set_mode(SCREEN_SIZE, 0, 32)

aviao_img = pygame.image.load("./images/aviao.png")
tiro_img = pygame.image.load("./images/tiro.png")
inimigo_img = pygame.image.load("./images/inimigo.png")


def testa_colisao( x1, x2, y1, y2, x3, x4, y3, y4 ):
    colisao_horizontal = False
    colisao_vertical = False
    if x3 < x2 and x1 <= x4:
        colisao_horizontal = True
    if y3 < y2 and y1 <= y4:
        colisao_vertical = True
    # return colisao_horizontal and colisao_vertical
    if colisao_horizontal and colisao_vertical:
        return True
    else:
        return False

jogando = True

# aviao = {"x": 0, "y": 300,
#          "vel_x": 0, "vel_y": 0, "imagem": aviao_img}

# inimigo = {"x": 0, "y": 10,
#          "vel_x": 0, "vel_y": 0, "imagem": inimigo_img,
#          "mostrar": True, "hp": 30}

aviao = Player( 0, 300, aviao_img )

inimigo_a = Inimigo()
inimigo_a.y = 10
inimigo_a.imagem = inimigo_img

# tiro = {"x": 0, "y": 600, "vel": 0,
#         "imagem": tiro_img, "mostrar": False, "dano": 10}
tiro = Tiro()
tiro.imagem = tiro_img

jogadores = pygame.sprite.Group()
jogadores.add( aviao )

while jogando:
    colidiu = False
    # Calcular as regras

    # Codigo que acelera o aviao
    jogadores.update()


    tiro.y = tiro.y + tiro.vel

    if inimigo_a.hp > 0: 
#                                  x1          x2              y1         y2
        colidiu = testa_colisao(tiro.x, tiro.x + 49, tiro.y, tiro.y + 54,
#                   x3           x4                 y3              y4
                  inimigo_a.x, inimigo_a.x + 64, inimigo_a.y, inimigo_a.y + 64)
    if colidiu:
        inimigo_a.hp = inimigo_a.hp - tiro.dano
        tiro.dano = 0

    
    # Desenhar a tela
    screen.fill( PRETO )
    jogadores.draw( screen )

    if inimigo_a.hp > 0:
        inimigo_a.mostrar(screen)

    if tiro.dano > 0:
        tiro.mostrar(screen)

    pygame.display.update()

    # Capturar os eventos
    for e in pygame.event.get():
        if e.type == pygame.QUIT:
            jogando = False
        elif e.type == pygame.KEYDOWN:
            if e.key == pygame.K_RIGHT:
                aviao.direita()
            elif e.key == pygame.K_LEFT:
                aviao.esquerda()
            elif e.key == pygame.K_UP:
                aviao.vel_y = -1
            elif e.key == pygame.K_DOWN:
                aviao.vel_y = 1
            elif e.key == pygame.K_f:
                tiro.dano = 10
                tiro.x = aviao.rect.x
                tiro.y = aviao.rect.y
                tiro.vel = -1
        elif e.type == pygame.KEYUP:
            if e.key == pygame.K_RIGHT:
                aviao.parar_horizontal()
            elif e.key == pygame.K_LEFT:
                aviao.parar_horizontal()
            elif e.key == pygame.K_UP:
                aviao.vel_y = 0
            elif e.key == pygame.K_DOWN:
                aviao.vel_y = 0
            

print("Fim do jogo")
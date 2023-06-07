# Labin - Trabalho Final

## Visão Geral do Software:
O objetivo principal do aplicativo proposto é fortalecer a integração entre os pesquisadores e laboratórios da UFMS, com o intuito de aumentar a quantidade de publicações e artigos científicos provenientes do centro de pesquisa. A ideia central é melhorar a visibilidade e o destaque científico da universidade por meio do compartilhamento de conhecimentos, recursos e colaborações entre os pesquisadores.

O software terá como foco a facilitação da colaboração e troca de informações entre os pesquisadores e laboratórios da UFMS. Serão fornecidas ferramentas que permitirão aos pesquisadores compartilhar detalhes de suas pesquisas em andamento, promover discussões, solicitar ajuda ou colaboração de outros pesquisadores e realizar revisões conjuntas de artigos científicos. Além disso, o software poderá fornecer recursos de rastreamento de citações, permitindo aos pesquisadores acompanhar a visibilidade e o impacto de suas publicações.

## Papéis:

- Administrador: O administrador terá permissões abrangentes no sistema. Ele será responsável por cadastrar, editar e excluir informações sobre todos os laboratórios e usuários do sistema. Além disso, terá a capacidade de vincular coordenadores a laboratórios e gerenciar as permissões de acesso de cada usuário.

- Coordenador: O coordenador terá permissões específicas relacionadas ao laboratório que ele supervisiona. Ele poderá cadastrar, editar e excluir informações sobre o seu laboratório, incluindo detalhes sobre as pesquisas em andamento. Além disso, o coordenador poderá vincular novos pesquisadores ao laboratório, facilitando a colaboração e o compartilhamento de conhecimentos.

- Pesquisador: O pesquisador será vinculado a um laboratório específico. Ele terá permissões para cadastrar, editar e excluir informações sobre suas pesquisas em andamento. Essas informações podem incluir detalhes sobre a metodologia, resultados e conclusões das pesquisas.

- Visitante: O visitante terá permissões limitadas no sistema. Ele poderá visualizar e pesquisar informações sobre todos os laboratórios, como os recursos disponíveis, as áreas de pesquisa e os projetos em andamento. No entanto, ele não terá permissão para fazer alterações ou adicionar conteúdo ao sistema.

Com esses papéis e funcionalidades, o aplicativo possibilitará uma melhor gestão e integração entre os cursos e laboratórios da universidade, permitindo a otimização dos recursos internos, o desenvolvimento de pesquisas mais colaborativas e o aumento da visibilidade da universidade por meio da publicação de artigos de impacto.

## Recursos

- Banco de dados: Ficará registrado os coordenadores, pesquisadores e o administrador.

- Mapas: Mostra onde fica os laboratórios ou grupo de pesquisa da Universidade.

- Imagens:
    * Cada imagem terá contentDescription.
    * Imagem do app diferente da configuração padrão do android.

- Fotos:
    * Usuário.
    * Grupo de pesquisa.

- Cores:
    * statusBarColor (theme)
    * background (xml do layout e color)
    * text (color)
    * button 

- String
    * Todas as string dentro deste recurso deverá ser comentado e agrupado para ficar manutenível.

- Menu

- Fragmentos: utilizado no mapa.

- RecyclerView personalizado: Este recurso mostrará foto do pesquisador, nome, email, grupo de pesquisa.
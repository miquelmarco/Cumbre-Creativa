package cumbrecreativa.cumbrecreativa.services.implement;

import cumbrecreativa.cumbrecreativa.models.Comment;
import cumbrecreativa.cumbrecreativa.repositories.CommentRepository;
import cumbrecreativa.cumbrecreativa.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImplement implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Override
    public void save(Comment comment) {
        commentRepository.save(comment);
    }
}